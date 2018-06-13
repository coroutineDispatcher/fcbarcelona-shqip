package com.stavro_xhardha.fcbarcelonashqip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.adapters.TopicsAdapter;
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.ExpandNewsSelectedTopicEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.Topic;

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

public class LatestNewsFragment extends Fragment {
    private SwipeRefreshLayout newsRefresher;
    private RecyclerView recyclerView;
    private ArrayList<Topic> topcsArrayList = new ArrayList<>();
    private TopicsAdapter topicsAdapter;
    final RecyclerView.LayoutManager manageer = new LinearLayoutManager(getActivity());
    private ExpandedNewsFragment expandedNewsFragment;
    private MaterialDialog materialDialog;
    private String newsBodyResponseString;

    public LatestNewsFragment() {
    }

    public static LatestNewsFragment newInstance() {
        return new LatestNewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setTitle(R.string.barca_news);
        }
        HomeActivity activity = (HomeActivity) getActivity();
        if (activity != null) {
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().show();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.latest_news_id);
        newsRefresher = view.findViewById(R.id.news_refresh);
        recyclerView.setLayoutManager(manageer);
        topicsAdapter = new TopicsAdapter(topcsArrayList);
        recyclerView.setAdapter(topicsAdapter);
        getNewsData();
        newsRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewsData();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest_news, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new SetFragmenTagEvent(Brain.LATEST_NEWS_FRAGMENT_TAG));
        EventBus.getDefault().post(new CheckNetworkEvent());
        getNewsData();
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
            getNewsData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ExpandNewsSelectedTopicEvent event) {
        if (event != null && getActivity() != null) {
            updateViews(event.getTopic());
            getNewsData();
            openExpandedNews(event.getTopic());
        }
    }

    private void openExpandedNews(Topic topic) {
        Brain.setNewsId(String.valueOf(topic.getId()));
        Brain.setImageEndpoint(String.valueOf(topic.getPhotoBase()));
        closeCustomFragment();
        boolean wrapInScrollView = true;
        materialDialog = new MaterialDialog.Builder(getActivity())
                .title(topic.getTitle())
                .customView(R.layout.custom_dialog_news, wrapInScrollView)
                .cancelable(false)
                .positiveText(R.string.close_dialog)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        closeCustomFragment();
                    }
                })
                .show();
        View view = materialDialog.getCustomView();
        if (view != null) {
            expandedNewsFragment = (ExpandedNewsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.custom_dialog_fragment);
        }
    }

    private void closeCustomFragment() {
        if (expandedNewsFragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(expandedNewsFragment).commit();
        }
    }

    private void updateViews(Topic topic) {
        if (getActivity() != null) {
            if (Brain.isNetworkAvailable(getActivity())) {
                new UpdateViewTask().execute(Brain.VIEWS_URL + String.valueOf(topic.getId()));
            } else {
                EventBus.getDefault().post(new CheckNetworkEvent());
            }
        }
    }

    private void getNewsData() {
        if (getActivity() != null) {
            if (Brain.isNetworkAvailable(getActivity())) {
                new GetNewsContentTask().execute(Brain.NEWS_URL);
            } else {
                EventBus.getDefault().post(new CheckNetworkEvent());
            }
        }
    }

    class GetNewsContentTask extends AsyncTask<String, String, String> {

        private ArrayList<Topic> topicResponse = null;
        int code;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (getActivity() != null) {
                newsRefresher.setRefreshing(true);
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
                    Type responseType = new TypeToken<ArrayList<Topic>>() {
                    }.getType();

                    topicResponse = gson.fromJson(reader, responseType);
                }
                code = response.code();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (topicResponse != null) {
                newsRefresher.setRefreshing(false);
                if (code == 200) {
                    topcsArrayList = topicResponse;
                    topicsAdapter.setItemsList(topcsArrayList);
                }
            } else {
                Toast.makeText(getActivity(), "Nuk mund të merren të dhënat", Toast.LENGTH_LONG).show();
            }
        }
    }

    class UpdateViewTask extends AsyncTask<String, String, String> {
        int responseFlag = 0;

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    responseFlag = 1;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (responseFlag == 1) {
            }
        }
    }
}