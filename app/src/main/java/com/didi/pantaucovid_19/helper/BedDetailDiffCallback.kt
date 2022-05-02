package com.didi.pantaucovid_19.helper

import androidx.recyclerview.widget.DiffUtil
import com.didi.pantaucovid_19.model.BedDetailItem

class BedDetailDiffCallback(
    private val mOldLisBedDetail: List<BedDetailItem>,
    private val mNewListBedDetail: List<BedDetailItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldLisBedDetail.size
    }

    override fun getNewListSize(): Int {
        return mNewListBedDetail.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldLisBedDetail[oldItemPosition].time == mNewListBedDetail[newItemPosition].time
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldLisBedDetail[oldItemPosition].stats
        val newItem = mNewListBedDetail[newItemPosition].stats
        return oldItem.title == newItem.title &&
                oldItem.bedAvailable == newItem.bedAvailable &&
                oldItem.bedEmpty == newItem.bedEmpty &&
                oldItem.queue == newItem.queue
    }
}