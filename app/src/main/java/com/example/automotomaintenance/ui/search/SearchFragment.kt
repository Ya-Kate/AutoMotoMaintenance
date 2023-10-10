package com.example.automotomaintenance.ui.search

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentSearchBinding
import com.example.automotomaintenance.ui.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapterSearch: SearchAdapter
    private lateinit var numberPhone: String
    private val viewModel: SearchViewModel by viewModels()

    private fun initView() {
        viewModel.loadListCompanies()
        adapterSearch = SearchAdapter(onPhoneClick)
        binding.listCompany.adapter = adapterSearch
        binding.listCompany.layoutManager = LinearLayoutManager(requireContext())
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapterSearch.submitList(it)
        }
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
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.search.editText?.doAfterTextChanged {
            viewModel.searchCompany(it.toString())
        }
        initView()
    }
}