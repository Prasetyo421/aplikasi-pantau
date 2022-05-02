package com.didi.pantaucovid_19.model

import com.google.gson.annotations.SerializedName

data class ResponseDataCovid(

    @field:SerializedName("missing_data")
    val missingData: Double,

    @field:SerializedName("tanpa_provinsi")
    val tanpaProvinsi: Int,

    @field:SerializedName("current_data")
    val currentData: Double,

    @field:SerializedName("list_data")
    val data: List<DataItem>,

    @field:SerializedName("last_date")
    val lastDate: String
)

data class KelompokUmurItem(

    @field:SerializedName("usia")
    val usia: Usia,

    @field:SerializedName("doc_count")
    val docCount: Int,

    @field:SerializedName("key")
    val key: String
)

data class JenisKelaminItem(

    @field:SerializedName("doc_count")
    val docCount: Int,

    @field:SerializedName("key")
    val key: String
)

data class Lokasi(

    @field:SerializedName("lon")
    val lon: Double,

    @field:SerializedName("lat")
    val lat: Double
)

data class DataItem(

    @field:SerializedName("penambahan")
    val penambahan: Penambahan,

    @field:SerializedName("doc_count")
    val docCount: Double,

    @field:SerializedName("lokasi")
    val lokasi: Lokasi,

    @field:SerializedName("jumlah_meninggal")
    val jumlahMeninggal: Int,

    @field:SerializedName("kelompok_umur")
    val kelompokUmur: List<KelompokUmurItem>,

    @field:SerializedName("jumlah_kasus")
    val jumlahKasus: Int,

    @field:SerializedName("jumlah_sembuh")
    val jumlahSembuh: Int,

    @field:SerializedName("jenis_kelamin")
    val jenisKelamin: List<JenisKelaminItem>,

    @field:SerializedName("key")
    val key: String,

    @field:SerializedName("jumlah_dirawat")
    val jumlahDirawat: Int
)

data class Usia(

    @field:SerializedName("value")
    val value: Double
)

data class Penambahan(

    @field:SerializedName("meninggal")
    val meninggal: Int,

    @field:SerializedName("positif")
    val positif: Int,

    @field:SerializedName("sembuh")
    val sembuh: Int
)
