package com.example.automotomaintenance.ui.moto.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.automotomaintenance.databinding.ItemVehicleBinding
import com.example.automotomaintenance.model.TransportVehicle

class MotorBikeViewHolder (private val binding: ItemVehicleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind (vehicle: TransportVehicle) {
        binding.run {
            brand.text = vehicle.brand
            number.text = vehicle.number
        }
    }
}
