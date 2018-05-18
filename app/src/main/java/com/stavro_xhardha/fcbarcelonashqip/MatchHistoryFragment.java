package com.stavro_xhardha.fcbarcelonashqip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stavro_xhardha.fcbarcelonashqip.adapters.HistoryMatchAdapter;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.MatchDetails;
import com.stavro_xhardha.fcbarcelonashqip.model.ResultResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.HISTORY_MATCH_FRAGMENT_TAG;


public class MatchHistoryFragment extends Fragment {
    private ArrayList<MatchDetails> details = new ArrayList<>();
    private HistoryMatchAdapter adapter;
    private RecyclerView rvMatchDetails;
    private SwipeRefreshLayout historyRefresh;

    public MatchHistoryFragment() {
    }

    public static MatchHistoryFragment newInstance() {
        return new MatchHistoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents(view);
        afterInitialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new SetFragmenTagEvent(HISTORY_MATCH_FRAGMENT_TAG));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshDataEvent event) {
        if (event != null) {
            getApiData();
        }
    }

    private void initializeComponents(View view) {
        rvMatchDetails = view.findViewById(R.id.history_rv);
        historyRefresh = view.findViewById(R.id.history_refresh);
    }

    private void afterInitialize() {
        prepareRecyclerView();
        getApiData();
        historyRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApiData();
            }
        });
    }

    private void prepareRecyclerView() {
        RecyclerView.LayoutManager manageer = new LinearLayoutManager(getActivity());
        rvMatchDetails.setLayoutManager(manageer);
        adapter = new HistoryMatchAdapter(details);
        rvMatchDetails.setAdapter(adapter);
    }

    private void getApiData() {
        String url = "http://api.football-data.org/v1/teams/81/fixtures";
        if (Brain.isNetworkAvailable(getContext())) {
            new GetMatchesAsync().execute(url);
        }
    }

    class GetMatchesAsync extends AsyncTask<String, String, String> {
        private ResultResponse mApiResponse;
        private int code;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (getActivity() != null) {
                historyRefresh.setRefreshing(true);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Request request = new Request.Builder()
                    .addHeader(Brain.HEADER_RESPONSE_CONTROL, Brain.RESPONSE_HEADER_VALUE)
                    .addHeader(Brain.AUTHORIZATION, Brain.TOKEN)
                    .url(strings[0])
                    .build();
            InputStream mInputStream = null;
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.isSuccessful()) {
                mInputStream = response.body().byteStream();
            }
            if (mInputStream != null) {
                Reader reader = new InputStreamReader(mInputStream);
                Type responseType = new TypeToken<ResultResponse<MatchDetails>>() {
                }.getType();
                mApiResponse = gson.fromJson(reader, responseType);
                code = response.code();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (mApiResponse != null) {
                historyRefresh.setRefreshing(false);
                if (code == 200) {
                    details = mApiResponse.getFixtures();
                    adapter.setItemList(details);
                    Collections.reverse(details);
                } else {
                    Toast.makeText(getActivity(), "Can't get table data", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.d(Brain.TAG, "Wrong");
            }
        }
    }
}