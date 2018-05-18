package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

public class VideoDetails {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("thumbnails")
    private VideoThumbnail thumbnails;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoThumbnail getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(VideoThumbnail thumbnails) {
        this.thumbnails = thumbnails;
    }
}
