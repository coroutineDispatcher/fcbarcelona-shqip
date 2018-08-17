package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

class PlayerResponse<T>(@SerializedName("count")
                        var count: Int = 0,
                        @SerializedName("players")
                        var players: ArrayList<T>? = null)