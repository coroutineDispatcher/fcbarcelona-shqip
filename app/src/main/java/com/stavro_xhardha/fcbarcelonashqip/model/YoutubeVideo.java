package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

public class YoutubeVideo {
    @SerializedName("id")
    private VideoID id;
    @SerializedName("snippet")
    private VideoDetails snippet;

    public VideoDetails getSnippet() {
        return snippet;
    }

    public void setSnippet(VideoDetails snippet) {
        this.snippet = snippet;
    }

    public VideoID getId() {
        return id;
    }

    public void setId(VideoID id) {
        this.id = id;
    }
}
