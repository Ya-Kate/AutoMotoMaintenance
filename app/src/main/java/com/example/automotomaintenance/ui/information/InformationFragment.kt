package com.example.automotomaintenance.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.bottomdialog.ItemShowInfoServiceDialog
import com.example.automotomaintenance.databinding.FragmentInformationBinding
import com.example.automotomaintenance.ui.information.adapter.InformationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private val viewModel: InformationViewModel by viewModels()
    private lateinit var adapterInfo: InformationAdapter
    private var showItemInfoService: ItemShowInfoServiceDialog? = null

    private fun initView() {
        viewModel.loadListInformation()
        adapterInfo = InformationAdapter(onClick)
        binding.listInfo.adapter = adapterInfo
        binding.listInfo.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listInformationDB.observe(viewLifecycleOwner) { list ->
            adapterInfo.submitList(list)
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
        initView()
    }

    private val onClick: (String) -> Unit = { idService ->
        showItemInfoService = ItemShowInfoServiceDialog.getInstance(childFragmentManager, idService)
        showItemInfoService?.show(childFragmentManager, ItemShowInfoServiceDialog.TAG)

        showItemInfoService?.onSuccess = {
            initView()
        }
    }
}
