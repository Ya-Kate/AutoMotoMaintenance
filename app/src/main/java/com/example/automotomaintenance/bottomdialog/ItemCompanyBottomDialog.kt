package com.example.automotomaintenance.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.automotomaintenance.databinding.DialogCompanyItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemCompanyBottomDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCompanyItemBinding

    var onDelete: (() -> Unit)? = null
    var onEdit: (() -> Unit)? = null

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
            onDelete?.invoke()
            dismiss()
        }

        binding.edit.setOnClickListener {
            onEdit?.invoke()
//            dismiss()
        }
    }
}