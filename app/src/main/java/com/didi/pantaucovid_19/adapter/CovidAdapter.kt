package com.didi.pantaucovid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.didi.pantaucovid_19.databinding.ItemCovidBinding
import com.didi.pantaucovid_19.helper.CovidDiffCallback
import com.didi.pantaucovid_19.model.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CovidAdapter : RecyclerView.Adapter<CovidAdapter.CovidViewHolder>() {
    private val listCovid: MutableList<DataItem> = mutableListOf()

    suspend fun setData(covid: List<DataItem>) {
        val diffCallback = CovidDiffCallback(this@CovidAdapter.listCovid, covid)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        withContext(Dispatchers.Main) {
            this@CovidAdapter.listCovid.clear()
            this@CovidAdapter.listCovid.addAll(covid)
            Timber.d("listCovidOld: ${this@CovidAdapter.listCovid.size}")
            diffResult.dispatchUpdatesTo(this@CovidAdapter)
        }
    }

    inner class CovidViewHolder(private val binding: ItemCovidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(province: DataItem) {
            with(binding) {
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