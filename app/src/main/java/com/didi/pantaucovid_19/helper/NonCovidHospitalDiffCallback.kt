package com.didi.pantaucovid_19.helper

import androidx.recyclerview.widget.DiffUtil
import com.didi.pantaucovid_19.model.HospitalsNonCovidItem

class NonCovidHospitalDiffCallback(val mOldListNonCovidHospital: List<HospitalsNonCovidItem>, val mNewListNonCovidHospital: List<HospitalsNonCovidItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldListNonCovidHospital.size
    }

    override fun getNewListSize(): Int {
        return mNewListNonCovidHospital.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListNonCovidHospital[oldItemPosition].id == mNewListNonCovidHospital[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldListNonCovidHospital[oldItemPosition]
        val newItem = mNewListNonCovidHospital[newItemPosition]

        return oldItem.address == newItem.address &&
                oldItem.name == newItem.name &&
                oldItem.availableBeds.size == newItem.availableBeds.size
    }
}