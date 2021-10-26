package com.didi.pantaucovid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.didi.pantaucovid_19.databinding.ItemHospitalBinding
import com.didi.pantaucovid_19.helper.CovidHospitalDiffCallback
import com.didi.pantaucovid_19.model.HospitalsCovidItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CovidHospitalAdapter : RecyclerView.Adapter<CovidHospitalAdapter.HospitalViewHolder>() {
    var listData = ArrayList<HospitalsCovidItem>()
    private var onItemClcikCallback: OnItemClickCallback? = null
    private var onPhoneClickCallback: OnPhoneClickCallback? = null

    suspend fun setData(listHospital: List<HospitalsCovidItem>){
        Timber.d("listDataOld: ${listData.size}, listDataNew: ${listHospital.size}")
        val diffCallback = CovidHospitalDiffCallback(this.listData, listHospital)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listData.clear()
        this.listData.addAll(listHospital)
        val adapter = this
        withContext(Dispatchers.Main){
            diffResult.dispatchUpdatesTo(adapter)
        }
    }

    inner class HospitalViewHolder(private val binding: ItemHospitalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HospitalsCovidItem){
            with(binding){
                tvHospitalName.text = item.name
                tvHospitalAddress.text = item.address
                tvBedAvailable.text = item.bedAvailability.toString()
                imgPhone.setOnClickListener {
                    onPhoneClickCallback?.onPhoneClicked(item)
                }
                itemView.setOnClickListener {
                    onItemClcikCallback?.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val binding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int  = listData.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClcikCallback = onItemClickCallback
    }

    fun setOnPhoneClickCallback(onPhoneClickCallback: OnPhoneClickCallback){
        this.onPhoneClickCallback = onPhoneClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(item: HospitalsCovidItem)
    }

    interface OnPhoneClickCallback{
        fun onPhoneClicked(item: HospitalsCovidItem)
    }
}