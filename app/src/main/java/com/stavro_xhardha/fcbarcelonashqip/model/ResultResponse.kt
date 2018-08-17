package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

class ResultResponse<T> {
    @SerializedName("season")
    var year: String? = null
    @SerializedName("count")
    var count: String? = null
    var fixtures: ArrayList<T>? = null
}
