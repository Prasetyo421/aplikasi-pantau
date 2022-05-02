package com.didi.pantaucovid_19.helper

import androidx.recyclerview.widget.DiffUtil
import com.didi.pantaucovid_19.model.HospitalsCovidItem

class CovidHospitalDiffCallback(
    val mOldListCovidHospital: List<HospitalsCovidItem>,
    val mNewListCovidHospital: List<HospitalsCovidItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldListCovidHospital.size
    }

    override fun getNewListSize(): Int {
        return mNewListCovidHospital.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListCovidHospital[oldItemPosition].id == mNewListCovidHospital[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldListCovidHospital[oldItemPosition]
        val newItem = mNewListCovidHospital[newItemPosition]
        return oldItem.name == newItem.name &&
                oldItem.address == newItem.address &&
                oldItem.phone == newItem.phone &&
                oldItem.bedAvailability == newItem.bedAvailability
    }


}