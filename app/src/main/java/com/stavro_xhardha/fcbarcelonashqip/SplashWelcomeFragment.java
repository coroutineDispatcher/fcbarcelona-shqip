package com.stavro_xhardha.fcbarcelonashqip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.stavro_xhardha.fcbarcelonashqip.events.OpenNewFragmentEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.StartCountDownEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.SCHEDULED_MATCHES_FRAGMENT_TAG;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.SPLASH_FRAGMENT_TAG;

public class SplashWelcomeFragment extends Fragment {
    private TextView tvTeamName;

    public SplashWelcomeFragment() {
    }

    public static SplashWelcomeFragment newInstance() {
        return new SplashWelcomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity activity = (HomeActivity) getActivity();
        if (activity != null) {
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().hide();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTeamName = view.findViewById(R.id.fc_barcelona_text);
        Animation animation   =    AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        animation.setDuration(500);
        tvTeamName.setAnimation(animation);
        animation.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_welcome, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new StartCountDownEvent());
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new SetFragmenTagEvent(SPLASH_FRAGMENT_TAG));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StartCountDownEvent event) {
        startCountdown();
    }

    private void startCountdown() {
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    EventBus.getDefault().post(new OpenNewFragmentEvent(TeamInfo.newInstance()));
                }
            }
        };
        timer.start();
    }
}
