package com.example.automotomaintenance.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.R
import com.example.automotomaintenance.ui.company.adapter.CompanyAdapter
import com.example.automotomaintenance.bottomdialog.CompanyBottomDialog
import com.example.automotomaintenance.databinding.FragmentCompanyBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CompanyFragment : Fragment() {

    private lateinit var binding: FragmentCompanyBinding
    private lateinit var adapterCompany: CompanyAdapter
    private val viewModel: CompanyViewModel by viewModels()

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterCompany = CompanyAdapter {

            CompanyBottomDialog().apply {
                onDeleteCompany = {
//                    val action =
//                        ListVehicleFragmentDirections.actionListVehicleFragmentToAddServiceFragment(
//                            numberAuto
//                        )
//                    findNavController().navigate(action)
                }

                onEditCompany = {
//                    val action =
//                        ListVehicleFragmentDirections.actionListVehicleFragmentToAutoFragment(
//                            numberAuto
//                        )
//                    findNavController().navigate(action)
                }
            }.show(childFragmentManager, "..")
        }
        binding.listCompany.adapter = adapterCompany
        binding.listCompany.layoutManager = LinearLayoutManager(requireContext())
        fifeBaseRepository.getCompany {
            adapterCompany.submitList(it)
        }

        binding.addCompany.setOnClickListener{
            findNavController().navigate(R.id.action_CompanyFragment_to_addCompanyFragment)
        }
    }
}