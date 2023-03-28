package com.example.automotomaintenance.ui.auto.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.automotomaintenance.model.TransportVehicle

class CarDiffUtil : DiffUtil.ItemCallback<TransportVehicle>() {
    override fun areItemsTheSame(oldItem: TransportVehicle, newItem: TransportVehicle): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: TransportVehicle, newItem: TransportVehicle): Boolean {
        return false
    }
}
