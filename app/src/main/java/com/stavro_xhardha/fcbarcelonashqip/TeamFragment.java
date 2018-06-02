package com.stavro_xhardha.fcbarcelonashqip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stavro_xhardha.fcbarcelonashqip.adapters.PlayersAdapter;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.Player;
import com.stavro_xhardha.fcbarcelonashqip.model.PlayerResponse;

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
import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.PLAYERS_URL;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.TEAM_FRAGMENT_TAG;


public class TeamFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Player> playerArrayList = new ArrayList<>();
    private PlayersAdapter playersAdapter;
    private SwipeRefreshLayout teamRefresh;

    public TeamFragment() {
    }

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setTitle(getResources().getString(R.string.title_team));
            EventBus.getDefault().post(new CheckNetworkEvent());
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
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeConponents(view);
        afterInitialize();

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new SetFragmenTagEvent(TEAM_FRAGMENT_TAG));
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

    private void initializeConponents(View view) {
        recyclerView = view.findViewById(R.id.rv_players);
        teamRefresh = view.findViewById(R.id.team_refresh);
    }

    private void afterInitialize() {
        prepareRecyclerView();
        getApiData();
        teamRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApiData();
            }
        });
    }

    private void getApiData() {
        if (Brain.isNetworkAvailable(getActivity())) {
            new GetPlayersAsync().execute(PLAYERS_URL);
        } else {
            EventBus.getDefault().post(new CheckNetworkEvent());
        }
    }

    private void prepareRecyclerView() {
        SwipeableTouchHelperCallback callback = new SwipeableTouchHelperCallback(new OnItemSwiped() {
            @Override
            public void onItemSwiped() {
                playersAdapter.removeTopElement();
                EventBus.getDefault().post(new CheckNetworkEvent());
            }

            @Override
            public void onItemSwipedLeft() {

            }

            @Override
            public void onItemSwipedRight() {

            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new SwipeableLayoutManager().setAngle(20).setAnimationDuratuion(1000).setMaxShowCount(6).setScaleGap(-10f).setTransYGap(20));
        recyclerView.setAdapter(playersAdapter = new PlayersAdapter(playerArrayList));
    }

    class GetPlayersAsync extends AsyncTask<String, String, String> {
        private PlayerResponse mStandingsResponse = null;
        int code;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (getActivity() != null) {
                teamRefresh.setRefreshing(true);
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
                    Type responseType = new TypeToken<PlayerResponse<Player>>() {
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
                teamRefresh.setRefreshing(false);
                if (code == 200) {
                    playerArrayList = mStandingsResponse.getPlayers();
                    playersAdapter.setItemList(playerArrayList);
                } else {
                }
            } else {
            }
        }
    }
}
