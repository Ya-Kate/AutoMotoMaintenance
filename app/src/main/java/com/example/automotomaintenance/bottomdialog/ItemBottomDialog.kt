package com.example.automotomaintenance.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.automotomaintenance.databinding.DialogItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemBottomDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogItemBinding
    var onShowClicked: (() -> Unit)? = null
    var onAddService: (() -> Unit)? = null
    var onDelete: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addInfo.setOnClickListener {
            onAddService?.invoke()
            dismiss()
        }
        binding.show.setOnClickListener {
            onShowClicked?.invoke()
            dismiss()
        }

        binding.delete.setOnClickListener {
            onDelete?.invoke()
        }
    }
}