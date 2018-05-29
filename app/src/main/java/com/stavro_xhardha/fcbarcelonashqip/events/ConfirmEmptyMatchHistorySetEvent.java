package com.stavro_xhardha.fcbarcelonashqip.events;

public class ConfirmEmptyMatchHistorySetEvent {
    private boolean isMatchSetEmpty;

    public ConfirmEmptyMatchHistorySetEvent(boolean isMatchSetEmpty){
        this.isMatchSetEmpty = isMatchSetEmpty;
    }

    public boolean isSetEmpty() {
        return isMatchSetEmpty;
    }
}
