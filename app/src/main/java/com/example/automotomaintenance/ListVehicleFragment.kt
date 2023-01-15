package com.example.automotomaintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.adapter.CarAdapter
import com.example.automotomaintenance.adapter.MotorBikeAdapter
import com.example.automotomaintenance.databinding.FragmentListVehicalBinding
import com.example.automotomaintenance.repository.FifeBaseRepository

class ListVehicleFragment : Fragment() {

    private lateinit var binding: FragmentListVehicalBinding
    private lateinit var adapterAuto: CarAdapter
    private lateinit var adapterMoto: MotorBikeAdapter
    private val fifeBaseRepository = FifeBaseRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListVehicalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterAuto = CarAdapter()
        binding.listAuto.adapter = adapterAuto
        binding.listAuto.layoutManager = LinearLayoutManager(requireContext())
        fifeBaseRepository.getCars {
            adapterAuto.submitList(it)
        }

        adapterMoto = MotorBikeAdapter()
        binding.listMoto.adapter = adapterMoto
        binding.listMoto.layoutManager = LinearLayoutManager(requireContext())
        fifeBaseRepository.getMotorBike {
            adapterMoto.submitList(it)
        }
    }
}