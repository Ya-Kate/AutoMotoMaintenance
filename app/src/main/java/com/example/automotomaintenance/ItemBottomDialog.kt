package com.example.automotomaintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.databinding.DialogItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemBottomDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogItemBinding
    var onShowClicked: (() -> Unit)? = null

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
            findNavController().navigate(R.id.addServiceFragment)
            dismiss()
        }
        binding.show.setOnClickListener{
            onShowClicked?.invoke()
            dismiss()
        }
    }

}