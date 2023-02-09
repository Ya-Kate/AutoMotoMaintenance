package com.example.automotomaintenance.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.automotomaintenance.model.Service

class ServiceDiffUtil : DiffUtil.ItemCallback<Service>() {
    override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
        return false
    }
}