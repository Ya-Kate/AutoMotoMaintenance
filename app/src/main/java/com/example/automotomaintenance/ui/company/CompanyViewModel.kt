package com.example.automotomaintenance.ui.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Company
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val listCompanies = MutableLiveData<List<Company>>()
    val isLoaderVisible = MutableLiveData<Boolean>()
    val company = MutableLiveData<List<Company>>()

    fun loadListCompanies() {
        isLoaderVisible.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val companies = fifeBaseRepository.loadCompanies()
            withContext(Dispatchers.Main) {
                listCompanies.postValue(companies)
                isLoaderVisible.value = false
            }
        }
    }

//    suspend fun loadOneCompany(idCompany: String) {
//
//        viewModelScope.launch(Dispatchers.IO) {
//            val loadCompany = fifeBaseRepository.loadOneCompany(idCompany)
//            withContext(Dispatchers.Main) {
//                company.postValue(loadCompany)
//            }
//        }
//    }

    fun deleteCompany(nameCompany: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fifeBaseRepository.deleteCompany(nameCompany)
            loadListCompanies()
        }
    }
}
