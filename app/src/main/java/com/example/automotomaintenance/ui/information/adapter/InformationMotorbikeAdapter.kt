package com.example.automotomaintenance.ui.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemInformationBinding
import com.example.automotomaintenance.model.InformationDB

class InformationMotorbikeAdapter (private val onClick: (String) -> Unit) :
    ListAdapter<InformationDB, InformationMotorbikeViewHolder>(InformationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationMotorbikeViewHolder {
        return InformationMotorbikeViewHolder(
            ItemInformationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: InformationMotorbikeViewHolder, position: Int) {
        getItem(position)?.let { infoDB ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(infoDB.id)
            }
        }
    }
}