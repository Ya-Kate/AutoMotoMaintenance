package com.example.automotomaintenance.bottomdialog.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.InformationDB
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemShowInfoServiceViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val infoService = MutableLiveData<List<InformationDB>>()

    fun getItemInfoService(idService: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val serviceInfo = fifeBaseRepository.loadOneInfoService(idService)

            withContext(Dispatchers.Main) {
                infoService.postValue(serviceInfo)
            }
        }
    }

    fun updateInfoService(km: String, idService: String) {
        fifeBaseRepository.upDateInfoService(km, idService)
    }
}