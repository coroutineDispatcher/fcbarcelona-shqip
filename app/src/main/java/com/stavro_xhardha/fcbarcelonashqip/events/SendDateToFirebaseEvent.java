package com.stavro_xhardha.fcbarcelonashqip.events;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class SendDateToFirebaseEvent {
    private ArrayList<DateTime> dateTime = new ArrayList<>();

    public SendDateToFirebaseEvent(ArrayList<DateTime> dateTime){
        this.dateTime = dateTime;
    }

    public ArrayList<DateTime> getDateTime() {
        return dateTime;
    }
}
