package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

public class YoutubePageInfo {
    @SerializedName("totalResults")
    private int pageResult;

    public int getPageResult() {
        return pageResult;
    }

    public void setPageResult(int pageResult) {
        this.pageResult = pageResult;
    }
}
