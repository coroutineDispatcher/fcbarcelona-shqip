package com.stavro_xhardha.fcbarcelonashqip.events;

import com.stavro_xhardha.fcbarcelonashqip.model.Topic;

public class ExpandNewsSelectedTopicEvent {
    private Topic topic;

    public ExpandNewsSelectedTopicEvent(Topic topic){
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }
}
