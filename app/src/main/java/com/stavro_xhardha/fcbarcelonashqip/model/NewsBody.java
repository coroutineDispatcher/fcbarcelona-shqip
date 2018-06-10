package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

public class NewsBody {
    @SerializedName("body")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
