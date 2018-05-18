package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class YouTubeResponse<T> {
    @SerializedName("items")
    private ArrayList<T> items;
    @SerializedName("nextPageToken")
    private String nextPageToken;
    @SerializedName("pageInfo")
    private YoutubePageInfo pageNumber;

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public YoutubePageInfo getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(YoutubePageInfo pageNumber) {
        this.pageNumber = pageNumber;
    }
}
