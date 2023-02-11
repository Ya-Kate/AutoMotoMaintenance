package com.example.automotomaintenance.ui.company.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.automotomaintenance.model.Company

class CompanyDiffUtil : DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return false
    }
}