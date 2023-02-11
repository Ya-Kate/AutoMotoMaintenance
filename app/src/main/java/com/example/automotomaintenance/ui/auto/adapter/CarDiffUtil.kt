package com.example.automotomaintenance.ui.auto.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.automotomaintenance.model.Vehicle

class CarDiffUtil : DiffUtil.ItemCallback<Vehicle>() {
    override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return false
    }
}