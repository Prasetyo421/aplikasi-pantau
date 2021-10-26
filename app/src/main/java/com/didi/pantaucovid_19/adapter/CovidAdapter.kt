package com.didi.pantaucovid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.didi.pantaucovid_19.databinding.ItemCovidBinding
import com.didi.pantaucovid_19.helper.CovidDiffCallback
import com.didi.pantaucovid_19.model.ListDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber

class CovidAdapter : RecyclerView.Adapter<CovidAdapter.CovidViewHolder>() {
    private val listCovid = ArrayList<ListDataItem>()

    suspend fun setData(listCovid: ArrayList<ListDataItem>){
        Timber.d("listCovidOld: ${this.listCovid.size}, listCovidNew: ${listCovid.size}")
        val diffCallback = CovidDiffCallback(this.listCovid, listCovid)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listCovid.clear()
        this.listCovid.addAll(listCovid)
        Timber.d("listCovidOld: ${this.listCovid.size}")
        val adapter = this
        withContext(Dispatchers.Main){
            diffResult.dispatchUpdatesTo(adapter)
        }
    }

    inner class CovidViewHolder(private val binding: ItemCovidBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(province: ListDataItem){
            with(binding){
                tvProvinceName.text = province.key
                tvAmountCases.text = province.jumlahKasus.toString()
                tvAmountDeath.text = province.jumlahMeninggal.toString()
                tvAmountRecover.text = province.jumlahSembuh.toString()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val binding = ItemCovidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CovidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {
        Timber.d("position: $position, max: $itemCount")
        holder.bind(listCovid[position])
    }

    override fun getItemCount(): Int = listCovid.size
}