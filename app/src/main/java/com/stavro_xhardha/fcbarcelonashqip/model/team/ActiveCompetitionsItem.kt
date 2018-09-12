package com.stavro_xhardha.fcbarcelonashqip.model.team

import com.google.gson.annotations.SerializedName

data class ActiveCompetitionsItem(

	@field:SerializedName("area")
	val area: Area? = null,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("plan")
	val plan: String? = null
)