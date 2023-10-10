package com.example.automotomaintenance.ui.auto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.R
import com.example.automotomaintenance.bottomdialog.ItemCompanyBottomDialog
import com.example.automotomaintenance.ui.service.adapter.ServiceCarAdapter
import com.example.automotomaintenance.databinding.FragmentAutoBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AutoFragment : Fragment() {

    private lateinit var binding: FragmentAutoBinding
    private lateinit var adapterService: ServiceCarAdapter
    private val viewModel: AutoViewModel by viewModels()

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AutoFragmentArgs by navArgs()
        val idCar = args.autoArg

        viewModel.loadOneAuto(idCar)
        viewModel.loadAutoServices(idCar)

        viewModel.infoOneAuto.observe(viewLifecycleOwner) { list ->
            val info = list.first()
            binding.brand.text = info.brand
            binding.number.text = info.number
            binding.year.text = getString(R.string.comma, info.year)
            binding.volume.text = getString(R.string.cc, info.volume)
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        adapterService = ServiceCarAdapter { idService ->

            ItemCompanyBottomDialog().apply {
                onDelete = {
                    viewModel.deleteServiceCar(idService, idCar)
                    viewModel.loadAutoServices(idCar)
                }

                onEdit = {
                    val action =
                        AutoFragmentDirections.actionAutoFragmentToServiceNoteCar(idCar, idService)
                    findNavController().navigate(action)
                    this.dismiss()
                }
            }.show(childFragmentManager, "..")

        }
        binding.listAuto.adapter = adapterService
        binding.listAuto.layoutManager = LinearLayoutManager(requireContext())
        viewModel.autoServices.observe(viewLifecycleOwner) { listServices ->
            adapterService.submitList(listServices)
        }
    }
}