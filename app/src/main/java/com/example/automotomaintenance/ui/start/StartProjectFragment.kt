package com.example.automotomaintenance.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentStartProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartProjectFragment : Fragment() {

    private lateinit var binding: FragmentStartProjectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_startProjectFragment2_to_registerFragment)
        }
    }
}
