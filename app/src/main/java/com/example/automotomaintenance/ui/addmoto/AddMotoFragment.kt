package com.example.automotomaintenance.ui.addmoto

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

            val buttonBrand = binding.brand
            val buttonNumber = binding.number
            val buttonYear = binding.year
            val buttonVolume = binding.volume

            val brand = buttonBrand.editText?.text.toString()
            val number = buttonNumber.editText?.text.toString()
            val year = buttonYear.editText?.text.toString()
            val volume = buttonVolume.editText?.text.toString()

            viewModel.addMoto(brand, number, year, volume)

            findNavController().navigate(R.id.action_addMotoFragment_to_AddVehiclesFragment)
        }
    }
}