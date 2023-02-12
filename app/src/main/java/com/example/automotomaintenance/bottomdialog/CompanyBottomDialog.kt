package com.example.automotomaintenance.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.automotomaintenance.databinding.DialogCompanyItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CompanyBottomDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCompanyItemBinding
    var onDeleteCompany: (() -> Unit)? = null
    var onEditCompany: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCompanyItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.delete.setOnClickListener {
            onDeleteCompany?.invoke()
            dismiss()
        }

        binding.edit.setOnClickListener{
            onEditCompany?.invoke()
            dismiss()
        }
    }
}