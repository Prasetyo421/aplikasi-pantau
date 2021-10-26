package com.didi.pantaucovid_19.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailHospital(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: Int
)

data class Data(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("bedDetail")
	val bedDetail: List<BedDetailItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)

data class Stats(

	@field:SerializedName("bed_empty")
	val bedEmpty: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("bed_available")
	val bedAvailable: Int,

	@field:SerializedName("queue")
	val queue: Int
)

data class BedDetailItem(

	@field:SerializedName("stats")
	val stats: Stats,

	@field:SerializedName("time")
	val time: String
)
