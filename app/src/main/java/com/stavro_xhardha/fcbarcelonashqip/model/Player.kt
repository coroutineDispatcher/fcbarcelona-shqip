package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

class Player(@SerializedName("name")
             var playerName: String? = null,
             @SerializedName("position")
             var position: String? = null,
             @SerializedName("jerseyNumber")
             var shirtNumber: String? = null,
             @SerializedName("dateOfBirth")
             var dateOfBirth: String? = null,
             @SerializedName("nationality")
             var nationality: String? = null,
             @SerializedName("contractUntil")
             var contractUntil: String? = null)
