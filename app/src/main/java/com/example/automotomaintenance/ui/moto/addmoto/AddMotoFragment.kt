package com.example.automotomaintenance.ui.moto.addmoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentAddMotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMotoFragment : Fragment() {

    private lateinit var binding: FragmentAddMotoBinding
    private val viewModel: AddMotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddMotoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addInfo.setOnClickListener {

            val brand = binding.brand.editText?.text.toString()
            val number = binding.number.editText?.text.toString()
            val year = binding.year.editText?.text.toString()
            val volume = binding.volume.editText?.text.toString()

            viewModel.addMoto(brand, number, year, volume)

            binding.brand.editText?.text = null
            binding.number.editText?.text = null
            binding.year.editText?.text = null
            binding.volume.editText?.text = null
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
