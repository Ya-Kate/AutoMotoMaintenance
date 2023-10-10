package com.example.automotomaintenance.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.bottomdialog.ItemShowInfoServiceDialog
import com.example.automotomaintenance.bottomdialog.ItemShowInfoServiceMotorbikeDialog
import com.example.automotomaintenance.databinding.FragmentInformationBinding
import com.example.automotomaintenance.ui.information.adapter.InformationAdapter
import com.example.automotomaintenance.ui.information.adapter.InformationMotorbikeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private val viewModel: InformationViewModel by viewModels()
    private lateinit var adapterInfo: InformationAdapter
    private lateinit var adapterMotorbikeInfo: InformationMotorbikeAdapter
    private var showItemInfoService: ItemShowInfoServiceDialog? = null
    private var showItemInfoMotorbikeService: ItemShowInfoServiceMotorbikeDialog? = null

    private fun initCarView() {
        viewModel.loadListInformation()
        adapterInfo = InformationAdapter(onClickCar)
        binding.listInfo.adapter = adapterInfo
        binding.listInfo.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listInformationDB.observe(viewLifecycleOwner) { listCarInfo ->
            adapterInfo.submitList(listCarInfo)
        }
    }

    private fun initMotorbikeView() {
        viewModel.loadListMotorbikeInformation()
        adapterMotorbikeInfo = InformationMotorbikeAdapter(onClickMotorbike)
        binding.listInfoMoto.adapter = adapterMotorbikeInfo
        binding.listInfoMoto.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listInformationMotorbikeDB.observe(viewLifecycleOwner) { listMotorbikeInfo ->
            adapterMotorbikeInfo.submitList(listMotorbikeInfo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCarView()
        initMotorbikeView()
    }

    private val onClickCar: (String) -> Unit = { idService ->
        showItemInfoService = ItemShowInfoServiceDialog.getInstance(childFragmentManager, idService)
        showItemInfoService?.show(childFragmentManager, ItemShowInfoServiceDialog.TAG)

        showItemInfoService?.onSuccess = {
            initCarView()
        }
    }
    private val onClickMotorbike: (String) -> Unit = { idService ->
        showItemInfoMotorbikeService =
            ItemShowInfoServiceMotorbikeDialog.getInstance(childFragmentManager, idService)
        showItemInfoMotorbikeService?.show(childFragmentManager, ItemShowInfoServiceDialog.TAG)

        showItemInfoMotorbikeService?.onSuccess = {
            initMotorbikeView()
        }
    }
}
