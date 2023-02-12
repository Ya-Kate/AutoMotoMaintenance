package com.example.automotomaintenance.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.automotomaintenance.databinding.DialogYesNoItemBinding

class ItemYesNoDialog : DialogFragment() {

    private lateinit var binding: DialogYesNoItemBinding

    var onYes: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogYesNoItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yes.setOnClickListener {
            onYes?.invoke()
            dismiss()
        }

        binding.no.setOnClickListener {
            dismiss()
        }
    }
}