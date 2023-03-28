package com.example.automotomaintenance.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.automotomaintenance.R
import com.example.automotomaintenance.bottomdialog.model.ItemShowViewModel
import com.example.automotomaintenance.databinding.DialogShowItemBinding
import com.example.automotomaintenance.model.Company
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_COMPANY_ID = "idCompany"

@AndroidEntryPoint
class ItemShowDialog : DialogFragment() {
    private lateinit var binding: DialogShowItemBinding
    private val viewModel: ItemShowViewModel by viewModels()

    companion object {
        val TAG = ItemShowDialog::class.java.name
        fun getInstance(fragmentManager: FragmentManager, idCompany: String): ItemShowDialog {
            val fragment: ItemShowDialog =
                (fragmentManager.findFragmentByTag(TAG) as? ItemShowDialog) ?: ItemShowDialog()
            val bundle = Bundle()
            bundle.putString(ARG_COMPANY_ID, idCompany)

            fragment.arguments = bundle

            return fragment
        }
    }

    private val idCompany: String get() = arguments?.getString(ARG_COMPANY_ID).orEmpty()

    var onSuccess: (() -> Unit)? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogShowItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.addCompanyListener()

        viewModel.getItemCompany(idCompany)
        viewModel.infoCompany.observe(viewLifecycleOwner) {
            val infoCompany: List<Company> = it

            binding.name.setText(infoCompany[0].name)
            binding.phone.setText(infoCompany[0].phone)
            binding.human.setText(infoCompany[0].person)
            binding.information.setText(infoCompany[0].information)
            binding.address.setText(infoCompany[0].address)
        }

        binding.success.setOnClickListener {
            val name = binding.name.text.toString()
            val phone = binding.phone.text.toString()
            val human = binding.human.text.toString()
            val information = binding.information.text.toString()
            val address = binding.address.text.toString()

            val listOldInfoCompany = mapOf(
                "idCompany" to idCompany,
                "name" to name,
                "phone" to phone,
                "human" to human,
                "information" to information,
                "address" to address
            )

            viewModel.validUpdateCompany(listOldInfoCompany)
            viewModel.validFail.observe(viewLifecycleOwner) {
                val valid = it
                if (!valid) {
                    val toast: Toast =
                        Toast.makeText(this.requireContext(), R.string.not_empty, Toast.LENGTH_LONG)
                    toast.show()
                }
            }

            viewModel.updateSuccess.observe(viewLifecycleOwner) {
                val updateCompany = it
                if (updateCompany) {
                    onSuccess?.invoke()
                }
            }
        }

        binding.fail.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.removeCompanyListener()
    }
}