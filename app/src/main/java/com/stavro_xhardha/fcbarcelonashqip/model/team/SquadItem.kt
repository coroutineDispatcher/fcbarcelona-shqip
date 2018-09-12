package com.stavro_xhardha.fcbarcelonashqip.model.team

import com.google.gson.annotations.SerializedName

data class SquadItem(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("nationality")
	val nationality: String? = null,

	@field:SerializedName("countryOfBirth")
	val countryOfBirth: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("dateOfBirth")
	val dateOfBirth: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("position")
	val position: String? = null
)