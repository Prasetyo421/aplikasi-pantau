package com.didi.pantaucovid_19.helper

import androidx.recyclerview.widget.DiffUtil
import com.didi.pantaucovid_19.model.CitiesItem

class CityDiffCallback(val mOldListCities: List<CitiesItem>, val mNewListCities: List<CitiesItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldListCities.size
    }

    override fun getNewListSize(): Int {
        return mNewListCities.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListCities[oldItemPosition].id == mNewListCities[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListCities[oldItemPosition].name == mNewListCities[newItemPosition].name
    }
}