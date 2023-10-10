package com.example.automotomaintenance.ui.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemInformationBinding
import com.example.automotomaintenance.model.InformationDB

class InformationAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<InformationDB, InformationViewHolder>(InformationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        return InformationViewHolder(
            ItemInformationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        getItem(position)?.let { infoDB ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(infoDB.id)
            }
        }
    }
}