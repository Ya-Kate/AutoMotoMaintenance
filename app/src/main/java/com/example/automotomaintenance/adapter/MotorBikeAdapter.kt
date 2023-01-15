package com.example.automotomaintenance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemVehicleBinding
import com.example.automotomaintenance.model.Vehicle

 class MotorBikeAdapter : ListAdapter<Vehicle, MotorBikeViewHolder>(MotorBikeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorBikeViewHolder {
        return MotorBikeViewHolder(
            ItemVehicleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MotorBikeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

