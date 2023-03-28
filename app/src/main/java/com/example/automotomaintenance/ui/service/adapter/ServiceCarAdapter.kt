package com.example.automotomaintenance.ui.service.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemServiceBinding
import com.example.automotomaintenance.model.Service

class ServiceCarAdapter(
    private val onClick: (idService: String) -> Unit
) : ListAdapter<Service, ServiceCarViewHolder>(ServiceDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceCarViewHolder {
        return ServiceCarViewHolder(
            ItemServiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ServiceCarViewHolder, position: Int) {
        getItem(position)?.let { service ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(service.idService)
            }
        }
    }
}
