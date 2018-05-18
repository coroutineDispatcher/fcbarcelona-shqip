package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

public class VideoThumbnail {
    @SerializedName("medium")
    private MediumVideoObject mediumVideoObject;

    public MediumVideoObject getMediumVideoObject() {
        return mediumVideoObject;
    }

    public void setMediumVideoObject(MediumVideoObject mediumVideoObject) {
        this.mediumVideoObject = mediumVideoObject;
    }
}
