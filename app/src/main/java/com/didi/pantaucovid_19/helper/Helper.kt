package com.didi.pantaucovid_19.helper

import android.widget.ImageView
import com.bumptech.glide.Glide

class Helper {
    companion object {
        val mapProvinces: Map<String, String> = mapOf(
            "Aceh" to "11prop",
            "Sumatera Utara" to "12prop",
            "Sumatera Barat" to "13prop",
            "Riau" to "14prop",
            "Jambi" to "15prop",
            "Sumatera Selatan" to "16prop",
            "Bengkulu" to "17prop",
            "Lampung" to "18prop",
            "Kepulauan Bangka Belitung" to "19prop",
            "Kepulauan Riau" to "20prop",
            "DKI Jakarta" to "31prop",
            "Jawa Barat" to "32prop",
            "Jawa Tengah" to "33prop",
            "DI Yogyakarta" to "34prop",
            "Jawa Timur" to "35prop",
            "Banten" to "36prop",
            "Bali" to "51prop",
            "Nusa Tenggara Barat" to "52prop",
            "Nusa Tenggara Timur" to "53prop",
            "Kalimantan Barat" to "61prop",
            "Kalimantan Tengah" to "62prop",
            "Kalimantan Selatan" to "63prop",
            "Kalimantan Timur" to "64prop",
            "Kalimantan Utara" to "65prop",
            "Sulawesi Utara" to "71prop",
            "Sulawesi Tengah" to "72prop",
            "Sulawesi Selatan" to "73prop",
            "Sulawesi Tenggara" to "74prop",
            "Gorontalo" to "75prop",
            "Sulawesi Barat" to "76prop",
            "Maluku" to "81prop",
            "Maluku Utara" to "82prop",
            "Papua Barat" to "91prop",
            "Papua" to "92prop",
        )

        fun ImageView.loadImage(url: String) {
            Glide.with(this.context)
                .load(url)
                .into(this)
        }
    }
}