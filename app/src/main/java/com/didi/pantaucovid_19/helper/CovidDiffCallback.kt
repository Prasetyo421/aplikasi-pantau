package com.didi.pantaucovid_19.helper

import androidx.recyclerview.widget.DiffUtil
import com.didi.pantaucovid_19.model.DataItem

class CovidDiffCallback(
    private val mOldCovid: List<DataItem>,
    private val mNewCovid: List<DataItem>
) : DiffUtil
.Callback() {
    override fun getOldListSize(): Int {
        return mOldCovid.size
    }

    override fun getNewListSize(): Int {
        return mNewCovid.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldCovid[oldItemPosition].key == mNewCovid[newItemPosition].key
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldCovid[oldItemPosition].jumlahKasus == mNewCovid[newItemPosition].jumlahKasus &&
                mOldCovid[oldItemPosition].jumlahMeninggal == mNewCovid[newItemPosition].jumlahMeninggal &&
                mOldCovid[oldItemPosition].jumlahSembuh == mNewCovid[newItemPosition].jumlahSembuh
    }
}