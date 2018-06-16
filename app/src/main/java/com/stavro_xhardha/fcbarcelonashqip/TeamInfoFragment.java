package com.stavro_xhardha.fcbarcelonashqip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent;

import org.greenrobot.eventbus.EventBus;

import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.FC_BARCELONA_PAGE_FRAGMENT_TAG;

public class TeamInfoFragment extends Fragment {

    public TeamInfoFragment() {
    }

    public static TeamInfoFragment newInstance() {
        return new TeamInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            EventBus.getDefault().post(new SetFragmentTitleEvent(getResources().getString(R.string.barcelona_team_info)));
        }
        EventBus.getDefault().post(new ControlToolbarVisibilityevent(true));
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new SetFragmenTagEvent(FC_BARCELONA_PAGE_FRAGMENT_TAG));
    }
}
