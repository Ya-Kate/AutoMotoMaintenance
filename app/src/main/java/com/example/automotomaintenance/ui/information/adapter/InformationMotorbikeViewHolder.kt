package com.example.automotomaintenance.ui.information.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.automotomaintenance.databinding.ItemInformationBinding
import com.example.automotomaintenance.model.InformationDB

class InformationMotorbikeViewHolder(
    private val binding: ItemInformationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: InformationDB) {
        binding.run {
            nameWork.text = item.nameWork
            km.text = buildString {
                append(item.intervalKM)
//                append(" km")
            }
        }
    }
}