package com.example.automotomaintenance.ui.auto.addauto

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentAddAutoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAutoFragment : Fragment() {

    private lateinit var binding: FragmentAddAutoBinding
    private val viewModel: AddAutoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAutoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addInfo.setOnClickListener {

            val brand = binding.brand.editText?.text.toString()
            val number = binding.number.editText?.text.toString()
            val year = binding.year.editText?.text.toString()
            val volume = binding.volume.editText?.text.toString()

            if(brand.isBlank() || number.isBlank() || year.isBlank()) {
                val toast:Toast = Toast.makeText(requireContext(), R.string.not_empty_add_transport, Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                viewModel.addAuto(brand, number, year, volume)

                binding.brand.editText?.text = null
                binding.number.editText?.text = null
                binding.year.editText?.text = null
                binding.volume.editText?.text = null
            }
        }

        binding.back.setOnClickListener {
            activity?.finish()
        }
    }
}