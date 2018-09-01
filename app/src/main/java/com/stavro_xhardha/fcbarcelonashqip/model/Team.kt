package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

class Team {

    @SerializedName("crestUrl")
    var crestUrl: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int = 0

    override fun toString(): String {
        return "Team{" +
                "crestUrl = '" + crestUrl + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                "}"
    }
}