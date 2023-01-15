package com.example.automotomaintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.automotomaintenance.databinding.FragmentAddMotoBinding
import com.example.automotomaintenance.repository.FifeBaseRepository

class AddMotoFragment : Fragment() {

    private lateinit var binding:FragmentAddMotoBinding
    private val fireBaseRepository = FifeBaseRepository()

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
            fireBaseRepository.addMotorBike(
                binding.brand.editText?.text.toString(),
                binding.number.editText?.text.toString(),
                binding.volume.editText?.text.toString(),
                binding.year.editText?.text.toString()
            )

        }
    }

}