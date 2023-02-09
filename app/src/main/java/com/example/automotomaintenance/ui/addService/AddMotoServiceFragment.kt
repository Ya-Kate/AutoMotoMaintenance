package com.example.automotomaintenance.ui.addService

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
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
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddMotoServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddMotoServiceFragmentArgs by navArgs()
        val number: String = args.numberArg

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_addMotoServiceFragment_to_ListVehicleFragment)
        }

        val cal = Calendar.getInstance()
        var dataService = cal.time

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                dataService = cal.time
                binding.data.text = getData(dataService)
            }
        }

        binding.chooseDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                DatePickerDialog(
                    requireContext(), dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        binding.addInfo.setOnClickListener {
            val km: Int = binding.km.editText?.text.toString().toInt()
            val date: Date = dataService
            val service: String = binding.service.editText?.text.toString()
            val cost: Int = binding.cost.editText?.text.toString().toInt()

            viewModel.addMotoService(km, date, service, cost, number)

            binding.km.editText?.text = null
            binding.data.text = null
            binding.service.editText?.text = null
            binding.cost.editText?.text = null
        }
    }
}