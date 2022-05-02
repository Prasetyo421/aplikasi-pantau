package com.didi.pantaucovid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.didi.pantaucovid_19.databinding.ItemHospitalBinding
import com.didi.pantaucovid_19.helper.NonCovidHospitalDiffCallback
import com.didi.pantaucovid_19.model.HospitalsNonCovidItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NonCovidHospitalAdapter : RecyclerView.Adapter<NonCovidHospitalAdapter.NonCovidViewHolder>() {
    private val listData = ArrayList<HospitalsNonCovidItem>()
    private var onItemClickCallback: OnItemClickCallback? = null
    private var onPhoneClickCallback: OnPhoneClickCallback? = null

    suspend fun setData(listHospital: List<HospitalsNonCovidItem>){
        val diffCallback = NonCovidHospitalDiffCallback(this.listData, listHospital)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        withContext(Dispatchers.Main){
            this@NonCovidHospitalAdapter.listData.clear()
            this@NonCovidHospitalAdapter.listData.addAll(listHospital)
            diffResult.dispatchUpdatesTo(this@NonCovidHospitalAdapter)
        }
    }

    inner class NonCovidViewHolder(val binding: ItemHospitalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HospitalsNonCovidItem){
            with(binding){
                var bedAvailable = 0
                item.availableBeds.forEach{
                    bedAvailable += it.available
                }
                tvBedAvailable.text = bedAvailable.toString()
                tvHospitalAddress.text = item.address
                tvHospitalName.text = item.name
                imgPhone.setOnClickListener {
                    onPhoneClickCallback?.onPhoneClicked(item)
                }
                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NonCovidHospitalAdapter.NonCovidViewHolder {
        val binding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NonCovidViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NonCovidHospitalAdapter.NonCovidViewHolder,
        position: Int
    ) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int  = listData.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setPhoneClickCallback(onPhoneClickCallback: OnPhoneClickCallback){
        this.onPhoneClickCallback = onPhoneClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(item: HospitalsNonCovidItem)
    }

    interface OnPhoneClickCallback{
        fun onPhoneClicked(item: HospitalsNonCovidItem)
    }

}