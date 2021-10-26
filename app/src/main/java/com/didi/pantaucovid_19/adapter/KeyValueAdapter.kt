package com.didi.pantaucovid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.didi.pantaucovid_19.databinding.SimpleItemBinding
import com.didi.pantaucovid_19.helper.CityDiffCallback
import com.didi.pantaucovid_19.model.CitiesItem

class KeyValueAdapter : RecyclerView.Adapter<KeyValueAdapter.KeyValueViewHolder>() {
    val listData = ArrayList<CitiesItem>()
    var itemClickCallback: OnItemClickCallback? = null

    fun setData(listCity: List<CitiesItem>){
        val diffCallback = CityDiffCallback(listData, listCity)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listData.clear()
        this.listData.addAll(listCity)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class KeyValueViewHolder(val binding: SimpleItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CitiesItem){
            binding.text1.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyValueViewHolder {
        val binding = SimpleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeyValueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeyValueViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    fun setOnItemClickCallback(itemClickCallback: OnItemClickCallback){
        this.itemClickCallback = itemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(item: CitiesItem)
    }
}