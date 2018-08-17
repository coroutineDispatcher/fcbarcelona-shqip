package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

class Topic(@SerializedName("id")
            var id: Int = 0,
            @SerializedName("type")
            var type: String? = null,
            @SerializedName("title")
            var title: String? = null,
            @SerializedName("date")
            var date: String? = null,
            @SerializedName("time")
            var time: String? = null,
            @SerializedName("description")
            var description: String? = null,
            @SerializedName("long_description")
            var longDescription: String? = null,
            @SerializedName("photo_base")
            var photoBase: String? = null,
            @SerializedName("views")
            var views: String? = null,
            @SerializedName("writer")
            var author: String? = null,
            @SerializedName("section")
            var section: String? = null)
