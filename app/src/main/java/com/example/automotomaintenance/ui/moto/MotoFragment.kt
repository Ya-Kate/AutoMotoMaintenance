package com.example.automotomaintenance.ui.moto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.ui.service.adapter.ServiceMotorBikeAdapter
import com.example.automotomaintenance.databinding.FragmentMotoBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MotoFragment : Fragment() {

    private val viewModel: MotoViewModel by viewModels()
    private lateinit var adapterService: ServiceMotorBikeAdapter
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
        viewModel.infoOneMotorbike(number)
        viewModel.getMotoService(number)

        viewModel.motorbike.observe(viewLifecycleOwner) { list ->
            val info = list.first()
                binding.brand.text = info.brand
                binding.number.text = info.number
                binding.year.text = info.year + ","
                binding.volume.text = info.volume + "cc"
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        adapterService = ServiceMotorBikeAdapter {}
        binding.listMoto.adapter = adapterService
        binding.listMoto.layoutManager = LinearLayoutManager(requireContext())
        viewModel.motoService.observe(viewLifecycleOwner) { listService ->
            adapterService.submitList(listService)
        }
    }
}

