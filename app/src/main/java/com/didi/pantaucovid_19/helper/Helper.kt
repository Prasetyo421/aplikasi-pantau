package com.didi.pantaucovid_19.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.didi.pantaucovid_19.model.ProvincesItem

class Helper {
    companion object {
        val listProvince = arrayListOf<ProvincesItem>(
            ProvincesItem("11prop", "Aceh"), ProvincesItem(
                "12prop",
                "Sumatera Utara"
            ), ProvincesItem(
                "13prop",
                "Sumatera Barat"
            ), ProvincesItem(
                "14prop",
                "Riau"
            ), ProvincesItem(
                "15prop",
                "Jambi"
            ), ProvincesItem(
                "16prop",
                "Sumatera Selatan"
            ), ProvincesItem(
                "17prop",
                "Bengkulu"
            ), ProvincesItem(
                "18prop",
                "Lampung"
            ), ProvincesItem(
                "19prop",
                "Kepulauan Bangka Belitung"
            ), ProvincesItem(
                "20prop",
                "Kepulauan Riau"
            ), ProvincesItem(
                "31prop",
                "DKI Jakarta"
            ), ProvincesItem(
                "32prop",
                "Jawa Barat"
            ), ProvincesItem(
                "33prop",
                "Jawa Tengah"
            ), ProvincesItem(
                "34prop",
                "DI Yogyakarta"
            ), ProvincesItem(
                "35prop",
                "Jawa Timur"
            ), ProvincesItem(
                "36prop",
                "Banten"
            ), ProvincesItem(
                "51prop",
                "Bali"
            ), ProvincesItem(
                "52prop",
                "Nusa Tenggara Barat"
            ), ProvincesItem(
                "53prop",
                "Nusa Tenggara Timur"
            ), ProvincesItem(
                "61prop",
                "Kalimantan Barat"
            ), ProvincesItem(
                "62prop",
                "Kalimantan Tengah"
            ), ProvincesItem(
                "63prop",
                "Kalimantan Selatan"
            ), ProvincesItem(
                "64prop",
                "Kalimantan Timur"
            ), ProvincesItem(
                "65prop",
                "Kalimantan Utara"
            ), ProvincesItem(
                "71prop",
                "Sulawesi Utara"
            ), ProvincesItem(
                "72prop",
                "Sulawesi Tengah"
            ), ProvincesItem(
                "73prop",
                "Sulawesi Selatan"
            ), ProvincesItem(
                "74prop",
                "Sulawesi Tenggara"
            ), ProvincesItem(
                "75prop",
                "Gorontalo"
            ), ProvincesItem(
                "76prop",
                "Sulawesi Barat"
            ), ProvincesItem(
                "81prop",
                "Maluku"
            ), ProvincesItem(
                "82prop",
                "Maluku Utara"
            ), ProvincesItem(
                "91prop",
                "Papua Barat"
            ), ProvincesItem("92prop", "Papua")
        )
        val listProvinceName = arrayListOf<String>(
            "Aceh",
            "Sumatera Utara",
            "Sumatera Barat",
            "Riau",
            "Jambi",
            "Sumatera Selatan",
            "Bengkulu",
            "Lampung",
            "Kepulauan Bangka Belitung",
            "Kepulauan Riau",
            "DKI Jakarta",
            "Jawa Barat",
            "Jawa Tengah",
            "DI Yogyakarta",
            "Jawa Timur",
            "Banten",
            "Bali",
            "Nusa Tenggara Barat",
            "Nusa Tenggara Timur",
            "Kalimantan Barat",
            "Kalimantan Tengah",
            "Kalimantan Selatan",
            "Kalimantan Timur",
            "Kalimantan Utara",
            "Sulawesi Utara",
            "Sulawesi Tengah",
            "Sulawesi Selatan",
            "Sulawesi Tenggara",
            "Gorontalo",
            "Sulawesi Barat",
            "Maluku",
            "Maluku Utara",
            "Papua Barat",
            "Papua",
        )

        fun ImageView.loadImage(url: String) {
            Glide.with(this.context)
                .load(url)
                .into(this)
        }
    }
}