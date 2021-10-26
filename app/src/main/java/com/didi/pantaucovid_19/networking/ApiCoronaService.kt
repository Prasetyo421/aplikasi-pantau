package com.didi.pantaucovid_19.networking

import com.didi.pantaucovid_19.model.ResponseDataCovid
import retrofit2.Call
import retrofit2.http.GET

interface ApiCoronaService {
    @GET("prov.json")
    fun getAllProvince(): Call<ResponseDataCovid>
}