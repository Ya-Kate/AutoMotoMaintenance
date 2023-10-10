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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListTransportFragment :
    Fragment() {

    private lateinit var binding: FragmentListVehicalBinding
    private lateinit var adapterAuto: CarAdapter
    private lateinit var adapterMoto: MotorBikeAdapter
    private val viewModel: ListTransportViewModel by viewModels()
    private var deleteDialog: ItemDeleteDialog? = null

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

        viewModel.loadListCars()
        viewModel.loadListMotorBikes()

        adapterAuto = CarAdapter {
            val idCar = it
            ItemBottomDialog().apply {
                onAddService = {
                    val action =
                        ListTransportFragmentDirections.actionListVehicleFragmentToAddServiceFragment(
                            idCar
                        )
                    findNavController().navigate(action)
                }

                onShowClicked = {
                    val action =
                        ListTransportFragmentDirections.actionListVehicleFragmentToAutoFragment(
                            idCar
                        )
                    findNavController().navigate(action)
                }

                onDelete = {
                    deleteDialog = ItemDeleteDialog()
                    deleteDialog?.let { dialog ->
                        dialog.onSuccess = {
                            viewModel.deleteCar(idCar)
                            viewModel.loadListCars()
                            this.dismiss()
                        }
                        dialog.show(childFragmentManager, "..")
                    }

                }
            }.show(childFragmentManager, "..")
        }
        binding.listAuto.adapter = adapterAuto
        binding.listAuto.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listCars.observe(viewLifecycleOwner) {
            adapterAuto.submitList(it)
        }

        adapterMoto = MotorBikeAdapter {
            val idMotorBike = it
            ItemBottomDialog().apply {
                onAddService = {
                    val action =
                        ListTransportFragmentDirections.actionListVehicleFragmentToAddMotoServiceFragment(
                            idMotorBike
                        )
                    findNavController().navigate(action)
                }

                onShowClicked = {
                    val action =
                        ListTransportFragmentDirections.actionListVehicleFragmentToMotoFragment(
                            idMotorBike
                        )
                    findNavController().navigate(action)
                }

                onDelete = {
                    deleteDialog = ItemDeleteDialog()
                    deleteDialog?.let { dialog ->
                        dialog.onSuccess = {
                            viewModel.deleteMotorBike(idMotorBike)
                            viewModel.loadListMotorBikes()
                            this.dismiss()
                        }
                        dialog.show(childFragmentManager, "..")
                    }
                }
            }.show(childFragmentManager, "..")
        }
        binding.listMoto.adapter = adapterMoto
        binding.listMoto.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listMotorBikes.observe(viewLifecycleOwner) {
            adapterMoto.submitList(it)
        }
    }
}
