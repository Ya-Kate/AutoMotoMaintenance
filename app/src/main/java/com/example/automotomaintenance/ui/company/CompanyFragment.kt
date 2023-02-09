package com.example.automotomaintenance.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.automotomaintenance.adapter.CompanyAdapter
import com.example.automotomaintenance.databinding.FragmentCompanyBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CompanyFragment : Fragment() {

    private lateinit var binding: FragmentCompanyBinding
    private lateinit var adapterCompany:CompanyAdapter
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




    }
}