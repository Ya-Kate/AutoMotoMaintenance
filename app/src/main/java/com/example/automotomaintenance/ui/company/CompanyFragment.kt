package com.example.automotomaintenance.ui.company

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automotomaintenance.R
import com.example.automotomaintenance.activity.AddCarsActivity
import com.example.automotomaintenance.activity.AddCompaniesActivity
import com.example.automotomaintenance.bottomdialog.ItemCompanyBottomDialog
import com.example.automotomaintenance.bottomdialog.ItemShowDialog
import com.example.automotomaintenance.databinding.FragmentCompanyBinding
import com.example.automotomaintenance.ui.company.adapter.CompanyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyFragment : Fragment() {

    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    private lateinit var binding: FragmentCompanyBinding
    private lateinit var adapterCompany: CompanyAdapter
    private val viewModel: CompanyViewModel by viewModels()
    private lateinit var numberPhone: String
    private var showItemDialog: ItemShowDialog? = null
    val id = MutableLiveData<String>()

    private fun initView() {
        viewModel.loadListCompanies()
        adapterCompany = CompanyAdapter(onClick, onPhoneClick)
        binding.listCompany.adapter = adapterCompany
        binding.listCompany.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listCompanies.observe(viewLifecycleOwner) {
            adapterCompany.submitList(it)
        }
    }


    private val onClick: (String) -> Unit = { idCompany ->

        ItemCompanyBottomDialog().apply {
            onDelete = {
                viewModel.deleteCompany(idCompany)
                this.dismiss()
            }

            onEdit = {
                showItemDialog = ItemShowDialog.getInstance(childFragmentManager, idCompany)
                showItemDialog?.let { dialog ->
                    dialog.onSuccess = {
                        this.dismiss()
                        initView()
                    }
                    dialog.show(childFragmentManager, ItemShowDialog.TAG)
                }
            }
        }.show(childFragmentManager, "..")
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
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = binding.progressBar
        viewModel.isLoaderVisible.observe(viewLifecycleOwner) {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }

        initView()

        binding.addCompany.setOnClickListener {
//            val intent = (Intent(activity, AddCompaniesActivity::class.java))
//            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)


            startActivity(Intent(activity, AddCompaniesActivity::class.java))
            Log.e("", "AAAAAAAAAAAAAAAAAAAAAAA")

        }

        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getBoolean("upDate")

            if(result) {
                viewModel.loadListCompanies()
            }
        }
    }

//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
////        viewModel.loadListCompanies()
//
//        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE){
//            if (resultCode == Activity.RESULT_CANCELED){
//                val returnString = data?.getBooleanExtra("bool", false)
//                if(returnString == true) {
//                    viewModel.loadListCompanies()
//                }
//            }
//        }
//    }
}
