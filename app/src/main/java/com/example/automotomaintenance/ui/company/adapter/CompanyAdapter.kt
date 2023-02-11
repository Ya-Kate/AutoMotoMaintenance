package com.example.automotomaintenance.ui.company.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemCompanyBinding
import com.example.automotomaintenance.model.Company

class CompanyAdapter(private val onClick: (name: String) -> Unit) :
    ListAdapter<Company, CompanyViewHolder>(CompanyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(
            ItemCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        getItem(position)?.let { company ->
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick(company.name)
            }
        }
    }

}