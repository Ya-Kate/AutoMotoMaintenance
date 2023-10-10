package com.example.automotomaintenance.ui.company.addcompany

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentAddCompanyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCompanyFragment : Fragment() {

    private lateinit var binding: FragmentAddCompanyBinding
    private val viewModel: AddCompanyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var change = false

        binding.addInfo.setOnClickListener {
            val name = binding.name.editText?.text.toString()
            val information = binding.information.editText?.text.toString()
            val phone = binding.phone.editText?.text.toString()
            val person = binding.person.editText?.text.toString()
            val address = binding.address.editText?.text.toString()

            if (name.isBlank() || information.isBlank() || phone.isBlank()) {
                val toast: Toast =
                    Toast.makeText(requireContext(), R.string.not_empty, Toast.LENGTH_LONG)
                toast.show()
            } else {
                viewModel.addCompany(name, information, phone, person, address)

                binding.name.editText?.text = null
                binding.information.editText?.text = null
                binding.phone.editText?.text = null
                binding.person.editText?.text = null
                binding.address.editText?.text = null

                change = true
            }
        }

        binding.back.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("upDate" to change))
            activity?.finish()
        }
    }
}