package com.example.automotomaintenance.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.automotomaintenance.bottomdialog.model.ItemShowInfoServiceViewModel
import com.example.automotomaintenance.databinding.DialogInfoServicesBinding
import com.example.automotomaintenance.model.InformationDB
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_SERVICE_ID = "idService"

@AndroidEntryPoint
class ItemShowInfoServiceDialog : DialogFragment() {
    private lateinit var binding: DialogInfoServicesBinding
    private val viewModel: ItemShowInfoServiceViewModel by viewModels()

    companion object {
        val TAG: String = ItemShowInfoServiceDialog::class.java.name
        fun getInstance(
            fragmentManager: FragmentManager,
            idService: String
        ): ItemShowInfoServiceDialog {
            val fragment: ItemShowInfoServiceDialog =
                (fragmentManager.findFragmentByTag(TAG) as? ItemShowInfoServiceDialog)
                    ?: ItemShowInfoServiceDialog()
            val bundle = Bundle()
            bundle.putString(ARG_SERVICE_ID, idService)
            fragment.arguments = bundle

            return fragment
        }
    }

    private val idService: String get() = arguments?.getString(ARG_SERVICE_ID).orEmpty()
    var onSuccess: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogInfoServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getItemInfoService(idService)

        viewModel.infoService.observe(viewLifecycleOwner) {
            val infoService: List<InformationDB> = it
            binding.name.text = infoService[0].nameWork
            binding.km.setText(infoService[0].intervalKM)
        }

        binding.success.setOnClickListener {
            val intervalKM = binding.km.text.toString()
            viewModel.updateInfoService(intervalKM, idService)
            onSuccess?.invoke()
            dismiss()
        }

        binding.fail.setOnClickListener {
            dismiss()
        }
    }
}