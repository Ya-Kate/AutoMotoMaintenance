package com.example.automotomaintenance.ui.company.addcompany

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCompanyViewModel @Inject constructor(private val repository: FifeBaseRepository) :
    ViewModel() {

    private var companyAdded: (() -> Unit)? = null

    fun addCompany(
        name: String,
        information: String,
        phone: String,
        person: String,
        address: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCompany(name, information, phone, person, address)
            companyAdded?.invoke()
        }
    }
}