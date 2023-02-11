package com.example.automotomaintenance.ui.addService.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemServiceBinding
import com.example.automotomaintenance.model.Service

class ServiceMotorBikeAdapter(
    private val onClick: (service: String) -> Unit
) : ListAdapter<Service, ServiceMotorBikeViewHolder>(ServiceDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceMotorBikeViewHolder {
        return ServiceMotorBikeViewHolder(
            ItemServiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ServiceMotorBikeViewHolder, position: Int) {
        getItem(position)?.let { service ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(service.service)
            }
        }
    }

}