package com.example.automotomaintenance.ui.transport

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentAddVehicleBinding
import com.example.automotomaintenance.ui.start.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransportFragment : Fragment() {

    private lateinit var binding: FragmentAddVehicleBinding
    private val viewModel: AddTransportViewModel by viewModels()

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

        viewModel.getUserEmail()

        viewModel.userEmail.observe(viewLifecycleOwner) { email ->
            binding.emailUser.text = email
        }

        binding.logout.setOnClickListener {
            viewModel.logout()
            route()
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
            findNavController().navigate(R.id.action_AddVehiclesFragment_to_addAutoFragment)
        }

        binding.addMoto.setOnClickListener {
            findNavController().navigate(R.id.action_AddVehiclesFragment_to_addMotoFragment)
        }
    }

    private fun route() {
        val mainNavController = (requireActivity() as? MainActivity)?.mainNavController
            ?: throw IllegalStateException("wrong activity")
        mainNavController.navigate(R.id.action_global_registerFragment)
    }
}

