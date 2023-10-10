package com.example.automotomaintenance.ui.auto.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.automotomaintenance.databinding.ItemVehicleBinding
import com.example.automotomaintenance.model.TransportVehicle

class CarViewHolder(private val binding: ItemVehicleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(vehicle: TransportVehicle) {
        binding.run {
            brand.text = vehicle.brand
            number.text = vehicle.number
        }
    }
}
