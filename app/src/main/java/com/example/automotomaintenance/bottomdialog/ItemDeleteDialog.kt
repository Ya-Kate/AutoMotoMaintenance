package com.example.automotomaintenance.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.automotomaintenance.databinding.DialogDeleteItemBinding

class ItemDeleteDialog : DialogFragment() {

    private lateinit var binding: DialogDeleteItemBinding
    var onSuccess: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDeleteItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.success.setOnClickListener {
            onSuccess?.invoke()
            dismiss()
        }

        binding.fail.setOnClickListener {
            dismiss()
        }
    }
}