package com.example.automotomaintenance.ui.moto.noteService

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
import com.example.automotomaintenance.databinding.FragmentNoteServiceMotoBinding
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.util.getData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NoteServiceMoto : Fragment() {
    private lateinit var binding: FragmentNoteServiceMotoBinding
    private val viewModel: NoteServiceMotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteServiceMotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: NoteServiceMotoArgs by navArgs()
        val idMoto = args.idMotoArg
        val idService = args.idServiceArg

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

        viewModel.addServiceMotorBikeListener()

        viewModel.loadNoteService(idMoto, idService)

        viewModel.noteServiceMotorBike.observe(viewLifecycleOwner) {
            val infoService: List<Service> = it
            binding.km.editText?.setText(infoService[0].km.toString())
            binding.data.text = getData(infoService[0].data)
            binding.service.editText?.setText(infoService[0].service)
            binding.cost.editText?.setText(infoService[0].cost)
        }

        viewModel.updateServiceSuccess.observe(viewLifecycleOwner) {
            val updateServiceMotorBike = it
            if (updateServiceMotorBike) {
                findNavController().popBackStack()
            }
        }

        binding.chooseDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.no.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.yes.setOnClickListener {

            val km: Int = binding.km.editText?.text.toString().toInt()
            val date: Date = dataService
            val service: String = binding.service.editText?.text.toString()
            val cost: String = binding.cost.editText?.text.toString()

            viewModel.validUpdateService(km, date, service, cost, idMoto, idService)
            viewModel.validFail.observe(viewLifecycleOwner) {
                val valid = it
                if (!valid) {
                    val toast: Toast =
                        Toast.makeText(this.requireContext(), R.string.not_empty, Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.removeServiceMotorBikeListener()
    }
}