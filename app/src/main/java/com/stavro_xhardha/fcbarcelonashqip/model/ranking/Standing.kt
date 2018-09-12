package com.stavro_xhardha.fcbarcelonashqip.model.ranking

import com.google.gson.annotations.SerializedName

data class Standing(@SerializedName("standings")
                    var standingsList: ArrayList<StandingResponse>)