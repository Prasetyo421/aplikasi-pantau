package com.didi.pantaucovid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.didi.pantaucovid_19.databinding.ItemBedDetailBinding
import com.didi.pantaucovid_19.helper.BedDetailDiffCallback
import com.didi.pantaucovid_19.model.BedDetailItem

class BedDetailAdapter : RecyclerView.Adapter<BedDetailAdapter.BedDetailViewHolder>() {
    val listData = ArrayList<BedDetailItem>()

    fun setData(listBedDetail: List<BedDetailItem>) {
        val diffCallback = BedDetailDiffCallback(this.listData, listBedDetail)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listData.clear()
        listData.addAll(listBedDetail)
        diffResult.dispatchUpdatesTo(this)
    }

    class BedDetailViewHolder(private val binding: ItemBedDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BedDetailItem) {
            with(binding) {
                tvTitle.text = item.stats.title
                tvBedAvailable.text = item.stats.bedAvailable.toString()
                tvTime.text = item.time
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BedDetailViewHolder {
        val binding =
            ItemBedDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BedDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BedDetailViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}