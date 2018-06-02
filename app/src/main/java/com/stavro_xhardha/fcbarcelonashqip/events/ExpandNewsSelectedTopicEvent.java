package com.stavro_xhardha.fcbarcelonashqip.events;

public class ExpandNewsSelectedTopicEvent {
    private int id;

    public ExpandNewsSelectedTopicEvent(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
