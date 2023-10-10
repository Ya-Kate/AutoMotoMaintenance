package com.example.automotomaintenance.ui.service

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentAddMotoServiceBinding
import com.example.automotomaintenance.util.getData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddMotoServiceFragment : Fragment() {

    lateinit var binding: FragmentAddMotoServiceBinding
    private val viewModel: AddMotoServiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddMotoServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddMotoServiceFragmentArgs by navArgs()
        val id: String = args.numberArg

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        val cal = Calendar.getInstance()
        var dataService = cal.time

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                dataService = cal.time
                binding.data.text = getData(dataService)
            }

        binding.chooseDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.addInfo.setOnClickListener {

            val km: Int = binding.km.editText?.text.toString().toInt()
            val date: Date = dataService
            val service: String = binding.service.editText?.text.toString()
            val cost: String = binding.cost.editText?.text.toString()

            if (service.isBlank() || cost.isBlank()) {
                val toast: Toast = Toast.makeText(
                    requireContext(),
                    R.string.not_empty_service,
                    Toast.LENGTH_LONG
                )
                toast.show()
            } else {
                viewModel.addMotoService(km, date, service, cost, id)

                binding.km.editText?.text = null
                binding.data.text = null
                binding.service.editText?.text = null
                binding.cost.editText?.text = null
            }
        }
    }
}

