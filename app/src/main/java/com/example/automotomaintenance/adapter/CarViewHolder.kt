package com.example.automotomaintenance.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.automotomaintenance.databinding.ItemVehicleBinding
import com.example.automotomaintenance.model.Vehicle

class CarViewHolder(private val binding: ItemVehicleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(vehicle: Vehicle) {
        binding.run {
            brand.text = vehicle.brand
            number.text = vehicle.number
        }
    }
}