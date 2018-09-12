package com.stavro_xhardha.fcbarcelonashqip.model.ranking

import com.google.gson.annotations.SerializedName

class StandingResponse {

    @SerializedName("stage")
    var stage: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("table")
    var table: List<TableItem>? = null

    @SerializedName("group")
    var group: Any? = null

    override fun toString(): String {
        return "StandingResponse{" +
                "stage = '" + stage + '\''.toString() +
                ",type = '" + type + '\''.toString() +
                ",table = '" + table + '\''.toString() +
                ",group = '" + group + '\''.toString() +
                "}"
    }
}