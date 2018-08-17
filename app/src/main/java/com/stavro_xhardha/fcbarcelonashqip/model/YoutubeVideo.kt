package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

class YoutubeVideo(@SerializedName("id")
                   var id: VideoID? = null,
                   @SerializedName("snippet")
                   var snippet: VideoDetails? = null)