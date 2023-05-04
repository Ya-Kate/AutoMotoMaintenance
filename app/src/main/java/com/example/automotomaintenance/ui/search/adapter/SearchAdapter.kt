package com.example.automotomaintenance.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.automotomaintenance.databinding.ItemCompanyBinding
import com.example.automotomaintenance.model.Company
import com.example.automotomaintenance.ui.company.adapter.CompanyDiffUtil

class SearchAdapter(
    private val onPhoneClick: (String) -> Unit
) : ListAdapter<Company, SearchViewHolder>(CompanyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { company ->
            holder.bind(getItem(position))
            holder.setOnPhoneClick(onPhoneClick)
        }
    }
}