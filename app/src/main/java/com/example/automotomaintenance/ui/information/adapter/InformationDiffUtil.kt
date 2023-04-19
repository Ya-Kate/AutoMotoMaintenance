package com.example.automotomaintenance.ui.information.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.automotomaintenance.model.InformationDB

class InformationDiffUtil:DiffUtil.ItemCallback<InformationDB>() {

    override fun areItemsTheSame(oldItem: InformationDB, newItem: InformationDB): Boolean {
//        return oldItem == newItem
        return false
    }

    override fun areContentsTheSame(oldItem: InformationDB, newItem: InformationDB): Boolean {
//        return oldItem == newItem
        return false
    }
}