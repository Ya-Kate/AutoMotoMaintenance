package com.example.automotomaintenance.ui.moto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemVehicleBinding
import com.example.automotomaintenance.model.Vehicle

class MotorBikeAdapter(
    private val onClick: (number: String) -> Unit
) : ListAdapter<Vehicle, MotorBikeViewHolder>(MotorBikeDiffUtil()) {

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
        getItem(position)?.let { vehicle ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(vehicle.number ?: "")
            }
        }
    }
}

