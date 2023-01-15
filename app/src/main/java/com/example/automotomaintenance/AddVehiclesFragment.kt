package com.example.automotomaintenance

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.databinding.FragmentAddVehicleBinding
import com.example.automotomaintenance.repository.AuthRepository

class AddVehiclesFragment : Fragment() {

    private val authRepository = AuthRepository()

    private lateinit var binding: FragmentAddVehicleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailUser.text = authRepository.getUserEmail()

        binding.logout.setOnClickListener {
            authRepository.logout()
//            findNavController().navigate(R.id.action_addVehiclesFragment_to_)
        }

        binding.addVehicle.setOnClickListener {
            val animation = ValueAnimator.ofFloat(0F, 1F)
            animation.addUpdateListener {
                binding.addAuto.alpha = it.animatedValue as Float
                binding.separator.alpha = it.animatedValue as Float
                binding.addMoto.alpha = it.animatedValue as Float
            }
            animation.duration = 2000
            animation.start()
        }

        binding.addAuto.setOnClickListener {
            findNavController().navigate(R.id.action_addVehiclesFragment_to_addAutoFragment)
        }

        binding.addMoto.setOnClickListener {
            findNavController().navigate(R.id.action_addVehiclesFragment_to_addMotoFragment)
        }

        binding.list.setOnClickListener {
            findNavController().navigate(R.id.action_addVehicle_to_listVehicleFragment)
        }

    }
}