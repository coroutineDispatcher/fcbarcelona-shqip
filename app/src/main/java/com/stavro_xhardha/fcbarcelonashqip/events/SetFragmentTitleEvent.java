package com.stavro_xhardha.fcbarcelonashqip.events;

import android.content.Context;

public class SetFragmentTitleEvent {
    private String fragmentTitle;

    public SetFragmentTitleEvent(String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }
}
