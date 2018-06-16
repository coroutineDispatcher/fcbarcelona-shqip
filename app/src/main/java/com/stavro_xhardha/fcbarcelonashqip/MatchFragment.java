package com.stavro_xhardha.fcbarcelonashqip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent;

import org.greenrobot.eventbus.EventBus;


public class MatchFragment extends Fragment {

    public MatchFragment() {
    }

    public static MatchFragment newInstance() {
        return new MatchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            EventBus.getDefault().post(new SetFragmentTitleEvent(getResources().getString(R.string.title_match)));
        }
        EventBus.getDefault().post(new ControlToolbarVisibilityevent(true));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        MatchFragmentTabAdapter adapter = new MatchFragmentTabAdapter(getContext(), getChildFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
