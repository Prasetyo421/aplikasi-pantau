package com.didi.pantaucovid_19.helper

import androidx.recyclerview.widget.DiffUtil
import com.didi.pantaucovid_19.database.PhoneNumber

class NumberDiffCallback(private val mOldEmergencyNumberList: List<PhoneNumber>, private val mNewEmergencyNumberList: List<PhoneNumber>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldEmergencyNumberList.size
    }

    override fun getNewListSize(): Int {
        return mNewEmergencyNumberList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldEmergencyNumberList[oldItemPosition].id == mNewEmergencyNumberList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = mOldEmergencyNumberList[oldItemPosition]
        val newList = mNewEmergencyNumberList[newItemPosition]
        return oldList.name == newList.name &&
                oldList.number == newList.number
    }

}