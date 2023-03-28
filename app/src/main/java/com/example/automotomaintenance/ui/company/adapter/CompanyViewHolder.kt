package com.example.automotomaintenance.ui.company.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.automotomaintenance.databinding.ItemCompanyBinding
import com.example.automotomaintenance.model.Company

class CompanyViewHolder(private val binding: ItemCompanyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(company: Company) {
        binding.run {
            name.text = company.name
            information.text = company.information
            phone.text = company.phone
            human.text = company.person
            address.text = company.address
        }
    }
    fun setOnPhoneClick(onFonClic :(String) -> Unit) {
        binding.phone.setOnClickListener{
            onFonClic.invoke(binding.phone.text.toString())
        }
    }
}
