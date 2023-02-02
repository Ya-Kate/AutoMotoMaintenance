package com.example.automotomaintenance.ui.moto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentMotoBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import com.example.automotomaintenance.ui.auto.AutoFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MotoFragment : Fragment() {

    private val viewModel: MotoViewModel by viewModels()
    private lateinit var binding: FragmentMotoBinding

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: MotoFragmentArgs by navArgs()
        val number = args.motoArg
        viewModel.getOneMotorbike(number)

        viewModel.motorbike.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                list.subList(0, 0)
                binding.brand.text = list[0].brand
                binding.number.text = list[0].number
                binding.year.text = list[0].year + ","
                binding.volume.text = list[0].volume + "cc"
            }
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_motoFragment_to_ListVehicleFragment)
        }

    }
}
