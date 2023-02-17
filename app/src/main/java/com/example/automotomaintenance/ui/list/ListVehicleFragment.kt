package com.example.automotomaintenance.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.bottomdialog.ItemBottomDialog
import com.example.automotomaintenance.bottomdialog.ItemDeleteDialog
import com.example.automotomaintenance.ui.auto.adapter.CarAdapter
import com.example.automotomaintenance.ui.moto.adapter.MotorBikeAdapter
import com.example.automotomaintenance.databinding.FragmentListVehicalBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListVehicleFragment @Inject constructor() :
    Fragment() {

    private lateinit var binding: FragmentListVehicalBinding
    private lateinit var adapterAuto: CarAdapter
    private lateinit var adapterMoto: MotorBikeAdapter
    private val viewModel: ListVehicleViewModel by viewModels()
    private var deleteDialog: ItemDeleteDialog? = null

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListVehicalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterAuto = CarAdapter {
            val numberAuto = it
            ItemBottomDialog().apply {
                onAddService = {
                    val action =
                        ListVehicleFragmentDirections.actionListVehicleFragmentToAddServiceFragment(
                            numberAuto
                        )
                    findNavController().navigate(action)
                }

                onShowClicked = {
                    val action =
                        ListVehicleFragmentDirections.actionListVehicleFragmentToAutoFragment(
                            numberAuto
                        )
                    findNavController().navigate(action)
                }


                onDelete = {
                    deleteDialog = ItemDeleteDialog()
                    deleteDialog?.let { dialog ->
                        dialog.onSuccess = {
                            fifeBaseRepository.deleteOneCar(numberAuto)
                            this.dismiss()
                        }
                        dialog.show(childFragmentManager, "..")
                    }

                }
            }.show(childFragmentManager, "..")
        }
        binding.listAuto.adapter = adapterAuto
        binding.listAuto.layoutManager = LinearLayoutManager(requireContext())
        fifeBaseRepository.getCars {
            adapterAuto.submitList(it)
        }

        adapterMoto = MotorBikeAdapter {
            val numberMoto = it
            ItemBottomDialog().apply {
                onAddService = {
                    val action =
                        ListVehicleFragmentDirections.actionListVehicleFragmentToAddMotoServiceFragment(
                            numberMoto
                        )
                    findNavController().navigate(action)
                }

                onShowClicked = {
                    val action =
                        ListVehicleFragmentDirections.actionListVehicleFragmentToMotoFragment(
                            numberMoto
                        )
                    findNavController().navigate(action)
                }

                onDelete = {
                    deleteDialog = ItemDeleteDialog()
                    deleteDialog?.let { dialog ->
                        dialog.onSuccess = {
                            fifeBaseRepository.deleteOneMotorbike(numberMoto)
                            this.dismiss()
                        }
                        dialog.show(childFragmentManager, "..")
                    }
                }

            }.show(childFragmentManager, "..")
        }
        binding.listMoto.adapter = adapterMoto
        binding.listMoto.layoutManager = LinearLayoutManager(requireContext())
        fifeBaseRepository.getMotorBike {
            adapterMoto.submitList(it)
        }
    }
}