package com.example.automotomaintenance.ui.company

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.R
import com.example.automotomaintenance.bottomdialog.ItemCompanyBottomDialog
import com.example.automotomaintenance.bottomdialog.ItemShowDialog
import com.example.automotomaintenance.databinding.FragmentCompanyBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import com.example.automotomaintenance.ui.company.adapter.CompanyAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CompanyFragment : Fragment() {

    private lateinit var binding: FragmentCompanyBinding
    private lateinit var adapterCompany: CompanyAdapter
    private val viewModel: CompanyViewModel by viewModels()
    private lateinit var numberPhone: String
    private var showItemDialog: ItemShowDialog? = null
    val id = MutableLiveData<String>()

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository


    private fun initView() {
        adapterCompany = CompanyAdapter(onClick, onPhoneClick)
        binding.listCompany.adapter = adapterCompany
        binding.listCompany.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listCompanies.observe(viewLifecycleOwner, Observer{
            adapterCompany.submitList(it)
        })
    }


    private val onClick: (String) -> Unit = { idCompany ->

        ItemCompanyBottomDialog().apply {
            onDelete = {
                viewModel.deleteCompany(idCompany)
                this.dismiss()
            }

            onEdit = {
                showItemDialog = ItemShowDialog.getInstance(childFragmentManager, idCompany)
                showItemDialog?.let { dialog ->
                    dialog.onSuccess = {
                        this.dismiss()
                        viewModel.loadListCompanies()
                    }
                    dialog.show(childFragmentManager, ItemShowDialog.TAG)
                }
            }
        }.show(childFragmentManager, "..")
    }

    private val onPhoneClick: (String) -> Unit = { phone ->
        numberPhone = phone
        if (numberPhone.isNotBlank()) {
            requestPermissionCallLauncher.launch(Manifest.permission.CALL_PHONE)
        }
    }

    private val requestPermissionCallLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${numberPhone}")
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), R.string.call_denied, Toast.LENGTH_LONG)
                    .show()
            }
        }

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

        val progressBar = binding.progressBar
        viewModel.isLoaderVisible.observe(viewLifecycleOwner) {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }

        viewModel.loadListCompanies()
        initView()

        binding.addCompany.setOnClickListener {
            findNavController().navigate(R.id.action_CompanyFragment_to_addCompanyFragment)
        }
    }
}
