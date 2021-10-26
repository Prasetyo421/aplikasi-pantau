package com.didi.pantaucovid_19.networking

import com.didi.pantaucovid_19.model.ResponseCities
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCitiesService {
    @GET("Kabkota")
    fun getCities(
        @Query("kode_propinsi") provinceId: String
    ): Call<ResponseCities>
}