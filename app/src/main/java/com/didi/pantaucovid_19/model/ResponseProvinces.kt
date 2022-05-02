package com.didi.pantaucovid_19.model

import com.google.gson.annotations.SerializedName

data class ResponseProvinces(

    @field:SerializedName("provinces")
    val provinces: List<ProvincesItem>
)

data class ProvincesItem(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,
)
