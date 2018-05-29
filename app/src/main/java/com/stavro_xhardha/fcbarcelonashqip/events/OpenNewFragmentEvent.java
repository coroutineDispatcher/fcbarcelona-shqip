package com.stavro_xhardha.fcbarcelonashqip.events;

import android.support.v4.app.Fragment;

public class OpenNewFragmentEvent {
    private Fragment fragment;

    public OpenNewFragmentEvent(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

}
