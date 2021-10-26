package com.didi.pantaucovid_19.model

import com.google.gson.annotations.SerializedName

data class ResponseCovidHospitals(

	@field:SerializedName("hospitals")
	val hospitalsCovid: List<HospitalsCovidItem>,

	@field:SerializedName("status")
	val status: Int
)

data class HospitalsCovidItem(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("bed_availability")
	val bedAvailability: Int,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("queue")
	val queue: Int,

	@field:SerializedName("info")
	val info: String
)

data class ParamDetailHospital(
	val id: String,
	val name: String,
	val type: String,
)
