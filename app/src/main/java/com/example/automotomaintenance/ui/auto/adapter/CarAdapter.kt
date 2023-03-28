package com.example.automotomaintenance.ui.auto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemVehicleBinding
import com.example.automotomaintenance.model.TransportVehicle

class CarAdapter(
    private val onClick: (idCar: String) -> Unit
) : ListAdapter<TransportVehicle, CarViewHolder>(CarDiffUtil()) {

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
        getItem(position)?.let { car ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(car.id)
            }
        }
    }
}