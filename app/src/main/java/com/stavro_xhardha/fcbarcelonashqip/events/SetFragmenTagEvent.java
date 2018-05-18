package com.stavro_xhardha.fcbarcelonashqip.events;

public class SetFragmenTagEvent {
    private String fragmentTag;

    public SetFragmenTagEvent(String tag){
        this.fragmentTag = tag;
    }

    public String getFragmentTag() {
        return fragmentTag;
    }
}
