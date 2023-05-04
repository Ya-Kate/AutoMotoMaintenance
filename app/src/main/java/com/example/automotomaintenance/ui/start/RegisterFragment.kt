package com.example.automotomaintenance.ui.start

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentRegisterBinding
import com.example.automotomaintenance.enum.ListCarEnum
import com.example.automotomaintenance.enum.ListMotorBikeEnum
import com.example.automotomaintenance.repository.AuthRepository
import com.example.automotomaintenance.ui.information.InformationViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var authRepository: AuthRepository
    private val viewModel: RegisterViewModel by viewModels()
    private val viewModelFB: InformationViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var inputText: String

        binding.loginButton.setOnClickListener {

            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            if (email.isBlank() || password.isBlank()) {
                inputText = activity?.getString(R.string.should_not_empty).toString()
                showException(inputText)
            } else {
                viewModel.login(email, password,
                    {
                        navigateToMainFragment()
                    },
                    { exception ->
                        inputText = exception.toString()
                        showException(inputText)
                    })
            }
        }

        binding.registerButton.setOnClickListener {

            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            if (email.isBlank() || password.isBlank()) {
                inputText = activity?.getString(R.string.should_not_empty).toString()
                showException(inputText)
            } else {
                viewModel.register(
                    email, password,
                    {
                        insertListInformationDB()
                        navigateToMainFragment()
                    },
                    { exception ->
                        inputText = exception.toString()
                        showException(inputText)
                    })
            }
        }
    }

    private fun navigateToMainFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_navigationMainFragment)
    }

    private fun insertListInformationDB() {

        val listInfo = mutableListOf(
            ListCarEnum.ONE.value,
            ListCarEnum.TWO.value,
            ListCarEnum.THREE.value,
            ListCarEnum.FOUR.value,
            ListCarEnum.FIVE.value,
            ListCarEnum.SIX.value,
            ListCarEnum.SEVEN.value,
            ListCarEnum.EIGHT.value,
            ListCarEnum.NIGHT.value,
            ListCarEnum.TEN.value,
            ListCarEnum.ELEVEN.value,
            ListCarEnum.TWELVE.value,
        )

        viewModelFB.addInfoServicesFB(listInfo)

        val listInfoMotorbike = mutableListOf(
            ListMotorBikeEnum.ONE.value,
            ListMotorBikeEnum.TWO.value,
            ListMotorBikeEnum.THREE.value,
            ListMotorBikeEnum.FOUR.value,
            ListMotorBikeEnum.FIVE.value,
            ListMotorBikeEnum.SIX.value,
            ListMotorBikeEnum.SEVEN.value,
        )
        viewModelFB.addInfoServicesMotorbikeFB(listInfoMotorbike)
    }

    private fun showException(value: String) {
        binding.errorText.text = value
        val animation = ValueAnimator.ofFloat(1F, 0F)
        animation.addUpdateListener { values ->
            binding.errorText.alpha = values.animatedValue as Float
        }
        animation.duration = 3000
        animation.start()
    }
}

