package com.example.automotomaintenance.ui.service.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.automotomaintenance.databinding.ItemServiceBinding
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.util.getData

class ServiceCarViewHolder(private val binding: ItemServiceBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(services: Service) {
        binding.run {
            km.text = services.km.toString() + " km"

            val dateFormat = getData(services.data)
            date.text = dateFormat

            service.text = services.service
            cost.text = services.cost.toString() + " $"
        }
    }
}