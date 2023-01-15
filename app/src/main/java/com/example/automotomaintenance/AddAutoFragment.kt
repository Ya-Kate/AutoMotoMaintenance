package com.example.automotomaintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.adapter.CarAdapter
import com.example.automotomaintenance.databinding.FragmentAddAutoBinding
import com.example.automotomaintenance.repository.FifeBaseRepository


class AddAutoFragment : Fragment() {

    private lateinit var binding: FragmentAddAutoBinding
    private val fireBaseRepository = FifeBaseRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addInfo.setOnClickListener {
            fireBaseRepository.addCar(
                binding.brand.editText?.text.toString(),
                binding.number.editText?.text.toString(),
                binding.volume.editText?.text.toString(),
                binding.year.editText?.text.toString()
            )
            findNavController().navigate(R.id.action_addAutoFragment_to_addVehicle)
        }
    }
}