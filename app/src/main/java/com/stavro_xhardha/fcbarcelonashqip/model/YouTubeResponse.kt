package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class YouTubeResponse<T>(@SerializedName("items")
                         var items: ArrayList<T>? = null,
                         @SerializedName("nextPageToken")
                         var nextPageToken: String? = null,
                         @SerializedName("pageInfo")
                         var pageNumber: YoutubePageInfo? = null)
