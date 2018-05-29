package com.stavro_xhardha.fcbarcelonashqip.events;

public class ConfirmEmptyMatchScheduleSetEvent {
    private boolean isMatchSetEmpty;

    public ConfirmEmptyMatchScheduleSetEvent(boolean isMatchSetEmpty){
        this.isMatchSetEmpty = isMatchSetEmpty;
    }

    public boolean isSetEmpty() {
        return isMatchSetEmpty;
    }
}
