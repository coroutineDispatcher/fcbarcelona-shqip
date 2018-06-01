package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

public class Topic {
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;
    @SerializedName("description")
    private String description;
    @SerializedName("long_description")
    private String longDescription;
    @SerializedName("photo_base")
    private String photoBase;
    @SerializedName("views")
    private String views;
    @SerializedName("writer")
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPhotoBase() {
        return photoBase;
    }

    public void setPhotoBase(String photoBase) {
        this.photoBase = photoBase;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
