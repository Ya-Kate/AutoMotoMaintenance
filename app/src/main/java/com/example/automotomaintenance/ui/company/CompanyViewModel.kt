package com.example.automotomaintenance.ui.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Company
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val fifeBaseRepository: FifeBaseRepository
) : ViewModel() {

    val listCompany = MutableLiveData<ArrayList<Company>>()

    fun getListCompany() {
        viewModelScope.launch(Dispatchers.IO) {
            fifeBaseRepository.getCompany {
                if (it.isNotEmpty()) {
                    listCompany.postValue(it)
                }
            }
        }
    }
}
