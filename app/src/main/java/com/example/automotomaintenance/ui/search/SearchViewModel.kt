package com.example.automotomaintenance.ui.search

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
class SearchViewModel @Inject constructor(
    private val fifeBaseRepository: FifeBaseRepository
) : ViewModel() {

    lateinit var listCompanies: List<Company>
    val searchResult = MutableLiveData<ArrayList<Company>?>()

    fun loadListCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            val companies = fifeBaseRepository.loadCompanies()
            withContext(Dispatchers.Main) {
                listCompanies = companies
            }
        }
    }

    fun searchCompany(searchText: String) {
        if(searchText.isNotBlank()){
            searchResult.value = listCompanies.filter {
                it.name.contains(searchText) || it.information.contains(searchText)
            } as ArrayList<Company>
        } else searchResult.postValue(null)

    }
}