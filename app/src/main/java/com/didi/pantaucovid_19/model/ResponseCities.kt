package com.didi.pantaucovid_19.model

import com.google.gson.annotations.SerializedName

data class ResponseCities(

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val cities: List<CitiesItem>

)

data class CitiesItem(

    @field:SerializedName("nama_kabkota")
    val name: String,

    @field:SerializedName("kode_kabkota")
    val id: String
)
