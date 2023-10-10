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
import com.example.automotomaintenance.databinding.FragmentAddAutoServiceBinding
import com.example.automotomaintenance.util.getData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddAutoServiceFragment : Fragment() {

    private lateinit var binding: FragmentAddAutoServiceBinding
    private val viewModel: AddAutoServiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAutoServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddAutoServiceFragmentArgs by navArgs()
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

                val kmEdit: String = binding.km.editText?.text.toString()
                val date: Date = dataService
                val service: String = binding.service.editText?.text.toString()
                val cost: String = binding.cost.editText?.text.toString()

                if (kmEdit.isBlank() || service.isBlank()) {
                    val toast: Toast = Toast.makeText(
                        requireContext(),
                        R.string.not_empty_service,
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                } else {
                    val km = kmEdit.toInt()
                    viewModel.addAutoService(km, date, service, cost, id)

                    binding.km.editText?.text = null
                    binding.data.text = null
                    binding.service.editText?.text = null
                    binding.cost.editText?.text = null
                }
        }
    }
}
