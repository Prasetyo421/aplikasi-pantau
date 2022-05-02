package com.didi.pantaucovid_19.model

import com.google.gson.annotations.SerializedName

data class ResponseNonCovidHospitals(

    @field:SerializedName("hospitals")
    val hospitalsCovid: List<HospitalsNonCovidItem>,

    @field:SerializedName("status")
    val status: Int
)

data class HospitalsNonCovidItem(

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("available_beds")
    val availableBeds: List<AvailableBedsItem>,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("info")
    val info: String
)

data class AvailableBedsItem(

    @field:SerializedName("bed_class")
    val bedClass: String,

    @field:SerializedName("room_name")
    val roomName: String,

    @field:SerializedName("available")
    val available: Int,

    @field:SerializedName("info")
    val info: String
)
