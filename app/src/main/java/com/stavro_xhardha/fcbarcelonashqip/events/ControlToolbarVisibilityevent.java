package com.stavro_xhardha.fcbarcelonashqip.events;

public class ControlToolbarVisibilityevent {
    private boolean showToolbar;

    public ControlToolbarVisibilityevent(boolean showToolbar){
        this.showToolbar = showToolbar;
    }

    public boolean isShowToolbar() {
        return showToolbar;
    }
}
