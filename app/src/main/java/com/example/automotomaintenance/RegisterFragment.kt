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

        binding.loginButton.setOnClickListener {
            authRepository.login(
                binding.email.editText?.text.toString().trim(),
                binding.password.editText?.text.toString().trim(), {
                    findNavController().navigate(R.id.action_registerFragment_to_navigationMainFragment)
                }, {
                    binding.errorText.text = it.toString()
                    val animation = ValueAnimator.ofFloat(1F, 0F)
                    animation.addUpdateListener { value ->
                        binding.errorText.alpha = value.animatedValue as Float
                    }
                    animation.duration = 3000
                    animation.start()
                }
            )
        }

        binding.registerButton.setOnClickListener {
            authRepository.register(
                binding.email.editText?.text.toString().trim(),
                binding.password.editText?.text.toString().trim(), {
                    findNavController().navigate(R.id.action_registerFragment_to_navigationMainFragment)
                }, {
                    binding.errorText.text = it.toString()
                    val animation = ValueAnimator.ofFloat(1F, 0F)
                    animation.addUpdateListener { value ->
                        binding.errorText.alpha = value.animatedValue as Float

                    }
                    animation.duration = 3000
                    animation.start()
                }
            )
        }

    }
}
