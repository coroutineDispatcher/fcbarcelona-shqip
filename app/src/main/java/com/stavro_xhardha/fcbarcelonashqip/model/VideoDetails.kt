package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

class VideoDetails(@SerializedName("title")
                   var title: String? = null,
                   @SerializedName("description")
                   var description: String? = null,
                   @SerializedName("thumbnails")
                   var thumbnails: VideoThumbnail? = null)