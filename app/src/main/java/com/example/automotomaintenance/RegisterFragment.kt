package com.example.automotomaintenance

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.databinding.FragmentRegisterBinding
import com.example.automotomaintenance.repository.AuthRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var authRepository: AuthRepository
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

        var inputText = ""

        binding.loginButton.setOnClickListener {

            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            if (email == "" || password == "") {
                inputText = "NOT BE EMPTY"
                showException(inputText)
            } else {
                authRepository.login(
                    email.trim(),
                    password.trim(), {
                        findNavController().navigate(R.id.action_registerFragment_to_navigationMainFragment)
                    }, { exception ->
                        inputText = exception.toString()
                        showException(inputText)
                    }
                )
            }
        }

        binding.registerButton.setOnClickListener {

            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            if (email == "" || password == "") {
                inputText = "NOT BE EMPTY"
                showException(inputText)
            } else {
                authRepository.register(
                    email.trim(),
                    password.trim(), {
                        findNavController().navigate(R.id.action_registerFragment_to_navigationMainFragment)
                    }, { exception ->
                        inputText = exception.toString()
                        showException(inputText)
                    }
                )
            }
        }
    }

    fun showException(value: String) {
        binding.errorText.text = value
        val animation = ValueAnimator.ofFloat(1F, 0F)
        animation.addUpdateListener { values ->
            binding.errorText.alpha = values.animatedValue as Float
        }
        animation.duration = 3000
        animation.start()
    }
}

