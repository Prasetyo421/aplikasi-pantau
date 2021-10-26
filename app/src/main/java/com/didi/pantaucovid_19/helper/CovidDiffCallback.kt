package com.didi.pantaucovid_19.helper

import androidx.recyclerview.widget.DiffUtil
import com.didi.pantaucovid_19.model.ListDataItem

class CovidDiffCallback(private val mOldListCovid: List<ListDataItem>, private val mNewListCovid: List<ListDataItem>) : DiffUtil
.Callback(){
    override fun getOldListSize(): Int {
        return mOldListCovid.size
    }

    override fun getNewListSize(): Int {
        return mNewListCovid.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListCovid[oldItemPosition].key == mNewListCovid[newItemPosition].key
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListCovid[oldItemPosition].jumlahKasus == mNewListCovid[newItemPosition].jumlahKasus &&
                mOldListCovid[oldItemPosition].jumlahMeninggal == mNewListCovid[newItemPosition].jumlahMeninggal &&
                mOldListCovid[oldItemPosition].jumlahSembuh == mNewListCovid[newItemPosition].jumlahSembuh
    }
}