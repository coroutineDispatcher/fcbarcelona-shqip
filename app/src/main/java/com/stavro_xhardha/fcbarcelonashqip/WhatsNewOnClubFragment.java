package com.stavro_xhardha.fcbarcelonashqip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.stavro_xhardha.fcbarcelonashqip.adapters.YoutubeVideoAdapter;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.YouTubeResponse;
import com.stavro_xhardha.fcbarcelonashqip.model.YoutubeVideo;

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

import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.WHATS_NEW_ON_CLUB_FRAGMENT_TAG;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.YOUTUBE_API_KEY;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.YOUTUBE_BASE_URL;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.YOUTUBE_DETAILS;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.YOUTUBE_PAGE_TOKEN;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.YOUTUBE_URL;


public class WhatsNewOnClubFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<YoutubeVideo> videosList = new ArrayList<>();
    private YoutubeVideoAdapter videoAdapter;
    private String nextPage = "";
    final RecyclerView.LayoutManager manageer = new LinearLayoutManager(getActivity());
    private SwipeRefreshLayout swipeRefreshLayout;

    public WhatsNewOnClubFragment() {
    }


    public static WhatsNewOnClubFragment newInstance() {
        return new WhatsNewOnClubFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setTitle(R.string.news);
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
        return inflater.inflate(R.layout.barcelona_news, container, false);
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
        EventBus.getDefault().post(new SetFragmenTagEvent(WHATS_NEW_ON_CLUB_FRAGMENT_TAG));
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
        recyclerView = view.findViewById(R.id.youtube_rv);
        swipeRefreshLayout = view.findViewById(R.id.news_refresher);
    }

    private void afterInitialize() {
        prepareRecyclerview();
        getApiData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApiData();
            }
        });
    }

    private void prepareRecyclerview() {
        recyclerView.setLayoutManager(manageer);
        videoAdapter = new YoutubeVideoAdapter(videosList);
        recyclerView.setAdapter(videoAdapter);
    }

    private void getApiData() {
        if (Brain.isNetworkAvailable(getActivity())) {
            new GetYoutubeContentTask().execute(YOUTUBE_URL + YOUTUBE_API_KEY);
        } else {
            EventBus.getDefault().post(new CheckNetworkEvent());
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    if (Brain.isNetworkAvailable(getActivity())) {
                        new GetYoutubeContentTask().execute(YOUTUBE_BASE_URL + YOUTUBE_PAGE_TOKEN + nextPage + "&"  + YOUTUBE_DETAILS  + YOUTUBE_API_KEY);
                    } else {
                        EventBus.getDefault().post(new CheckNetworkEvent());
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    class GetYoutubeContentTask extends AsyncTask<String, String, String> {

        private YouTubeResponse mYoutubeResponse = null;
        int code;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (getActivity() != null) {
                swipeRefreshLayout.setRefreshing(true);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                OkHttpClient client = new OkHttpClient();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Request request = new Request.Builder()
                        .url(strings[0])
                        .build();

                InputStream mInputStream = null;
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    mInputStream = response.body().byteStream();
                }
                if (mInputStream != null) {
                    Reader reader = new InputStreamReader(mInputStream);
                    Type responseType = new TypeToken<YouTubeResponse<YoutubeVideo>>() {
                    }.getType();

                    mYoutubeResponse = gson.fromJson(reader, responseType);
                }
                code = response.code();
            } catch (IOException e) {
                e.printStackTrace();
                mYoutubeResponse = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            swipeRefreshLayout.setRefreshing(false);
            if (mYoutubeResponse != null) {
                if (code == 200) {
                    videoAdapter.addArray(mYoutubeResponse.getItems());
                    nextPage = mYoutubeResponse.getNextPageToken();
                } else {
                    Toast.makeText(getActivity(), "Can't get table data", Toast.LENGTH_SHORT).show();
                }
            } else
                Snackbar.make(getView(), "Dicka Shkoi Gabim" , Snackbar.LENGTH_LONG).show();
                Log.d(Brain.TAG, "Wrong");
        }
    }
}
