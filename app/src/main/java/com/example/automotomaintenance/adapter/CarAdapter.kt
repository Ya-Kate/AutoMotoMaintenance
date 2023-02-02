package com.example.automotomaintenance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.ItemBottomDialog
import com.example.automotomaintenance.databinding.ItemVehicleBinding
import com.example.automotomaintenance.model.Vehicle
import com.example.automotomaintenance.ui.list.ListVehicleFragment

class CarAdapter(
    private val onClick: (number: String) -> Unit
) : ListAdapter<Vehicle, CarViewHolder>(CarDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemVehicleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        getItem(position)?.let { vehicle ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(vehicle.number ?: "")

            }
        }
    }
}