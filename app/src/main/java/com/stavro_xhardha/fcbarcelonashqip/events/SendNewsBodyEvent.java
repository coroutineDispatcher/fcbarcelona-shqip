package com.stavro_xhardha.fcbarcelonashqip.events;

public class SendNewsBodyEvent {
    private String newsBody;

    public SendNewsBodyEvent(String newsBody){
        this.newsBody = newsBody;
    }

    public String getNewsBody() {
        return newsBody;
    }
}
