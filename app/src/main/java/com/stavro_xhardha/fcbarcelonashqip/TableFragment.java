package com.stavro_xhardha.fcbarcelonashqip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.stavro_xhardha.fcbarcelonashqip.adapters.StandingsAdapter;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.StandingsResponse;
import com.stavro_xhardha.fcbarcelonashqip.model.Standing;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.TABLE_FRAGMENT_TAG;


public class TableFragment extends Fragment {
    private ArrayList<Standing> standings = new ArrayList<>();
    private StandingsAdapter adapter;
    private RecyclerView rvTable;
    private SwipeRefreshLayout tableRefresh;


    public TableFragment() {
    }

    public static TableFragment newInstance() {
        return new TableFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setTitle(getResources().getString(R.string.title_ranking));
        }
        HomeActivity activity = (HomeActivity) getActivity();
        if (activity != null) {
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents(view);
        afterInitialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new SetFragmenTagEvent(TABLE_FRAGMENT_TAG));
        EventBus.getDefault().post(new CheckNetworkEvent());
        getApiData();
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
        rvTable = view.findViewById(R.id.table_rv);
        tableRefresh = view.findViewById(R.id.table_refresh);
    }

    private void afterInitialize() {
        prepareRecyclerview();
        getApiData();
        tableRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApiData();
            }
        });
    }

    private void prepareRecyclerview() {
        RecyclerView.LayoutManager manageer = new LinearLayoutManager(getActivity());
        rvTable.setLayoutManager(manageer);
        adapter = new StandingsAdapter(standings);
        rvTable.setAdapter(adapter);
    }

    private void getApiData() {
        String url = "http://api.football-data.org/v1/competitions/455/leagueTable/";
        if (Brain.isNetworkAvailable(getActivity())) {
            new GetRankingDataTask().execute(url);
        }else{
            EventBus.getDefault().post(new CheckNetworkEvent());
        }
    }


    class GetRankingDataTask extends AsyncTask<String, String, String> {

        private StandingsResponse mStandingsResponse = null;
        int code;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (getActivity() != null) {
                tableRefresh.setRefreshing(true);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                OkHttpClient client = new OkHttpClient();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Request request = new Request.Builder()
                        .addHeader(Brain.HEADER_RESPONSE_CONTROL, Brain.RESPONSE_HEADER_VALUE)
                        .addHeader(Brain.AUTHORIZATION, Brain.TOKEN)
                        .url(strings[0])
                        .build();

                InputStream mInputStream = null;
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    mInputStream = response.body().byteStream();
                }
                if (mInputStream != null) {
                    Reader reader = new InputStreamReader(mInputStream);
                    Type responseType = new TypeToken<StandingsResponse<Standing>>() {
                    }.getType();

                    mStandingsResponse = gson.fromJson(reader, responseType);
                }
                code = response.code();
            } catch (IOException e) {
                e.printStackTrace();
                mStandingsResponse = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (mStandingsResponse != null) {
                tableRefresh.setRefreshing(false);
                if (code == 200) {
                    standings = mStandingsResponse.getStanding();
                    adapter.setItemsList(standings);
                } else {
                    Snackbar.make(getView() , getResources().getString(R.string.can_not_get_data) , Snackbar.LENGTH_LONG ).show();
                }
            } else
                Snackbar.make(getView() , getResources().getString(R.string.can_not_get_data) , Snackbar.LENGTH_LONG ).show();
        }
    }
}
