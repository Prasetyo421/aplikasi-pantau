package com.didi.pantaucovid_19.networking

import com.didi.pantaucovid_19.model.ResponseCities
import com.didi.pantaucovid_19.model.ResponseCovidHospitals
import com.didi.pantaucovid_19.model.ResponseDetailHospital
import com.didi.pantaucovid_19.model.ResponseNonCovidHospitals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHospitalService {
    @GET("get-cities")
    fun getCities(
        @Query("provinceid") provinceId: String
    ): Call<ResponseCities>

    @GET("get-hospitals")
    fun getCovidHospitals(
        @Query("provinceid") provinceId: String,
        @Query("cityid") cityId: String,
        @Query("type") type: String
    ): Call<ResponseCovidHospitals>

    @GET("get-hospitals")
    fun getNonCovidHospitals(
        @Query("provinceid") provinceId: String,
        @Query("cityid") cityId: String,
        @Query("type") type: String
    ): Call<ResponseNonCovidHospitals>

    @GET("get-bed-detail")
    fun getDetailHospital(
        @Query("hospitalid") hospitalId: String,
        @Query("type") type: String
    ): Call<ResponseDetailHospital>
}