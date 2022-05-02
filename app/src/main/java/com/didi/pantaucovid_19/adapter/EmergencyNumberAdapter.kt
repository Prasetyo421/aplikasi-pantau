package com.didi.pantaucovid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.didi.pantaucovid_19.database.PhoneNumber
import com.didi.pantaucovid_19.databinding.ItemEmergencyNumberBinding
import com.didi.pantaucovid_19.helper.NumberDiffCallback

class EmergencyNumberAdapter :
    RecyclerView.Adapter<EmergencyNumberAdapter.EmergencyNumberViewHolder>() {
    private val listNumber = ArrayList<PhoneNumber>()
    private var onItemClickCallback: OnPhoneClickCallback? = null

    fun setData(listNumber: List<PhoneNumber>) {
        val diffCallback = NumberDiffCallback(this.listNumber, listNumber)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNumber.clear()
        this.listNumber.addAll(listNumber)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class EmergencyNumberViewHolder(private val binding: ItemEmergencyNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(number: PhoneNumber) {
            with(binding) {
                tvNumberName.text = number.name
                tvNumber.text = number.number
                imgPhoneEnable.setOnClickListener {
                    onItemClickCallback?.onClicked(number)
                }
            }
        }
    }

    fun setOnPhoneClickCallback(onItemClickCallback: OnPhoneClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnPhoneClickCallback {
        fun onClicked(number: PhoneNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyNumberViewHolder {
        val binding =
            ItemEmergencyNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmergencyNumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmergencyNumberViewHolder, position: Int) {
        holder.bind(listNumber[position])
    }

    override fun getItemCount(): Int = listNumber.size

}