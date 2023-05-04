package com.example.automotomaintenance.ui.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.InformationDB
import com.example.automotomaintenance.repository.DataBaseRepository
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var dataBaseRepository: DataBaseRepository

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository

    val listInformationDB = MutableLiveData<List<InformationDB>>()
    val listInformationMotorbikeDB = MutableLiveData<List<InformationDB>>()

    fun loadListInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            val allInfoServiceFireBase = fifeBaseRepository.loadInfoServices()
            dataBaseRepository.addListInformation(allInfoServiceFireBase)

            withContext(Dispatchers.Main) {
                listInformationDB.postValue(allInfoServiceFireBase)
            }
        }
    }
    fun loadListMotorbikeInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            val allInfoServiceMotorbikeFireBase = fifeBaseRepository.loadInfoMotorbikeServices()
            dataBaseRepository.addListMotorbikeInformation(allInfoServiceMotorbikeFireBase)

            withContext(Dispatchers.Main) {
                listInformationMotorbikeDB.postValue(allInfoServiceMotorbikeFireBase)
            }
        }
    }

    fun addInfoServicesFB(listInfo: List<InformationDB>) {
        viewModelScope.launch(Dispatchers.IO) {
            fifeBaseRepository.addInfoServices(listInfo)
        }
    }

    fun addInfoServicesMotorbikeFB(listInfo: List<InformationDB>) {
        viewModelScope.launch(Dispatchers.IO) {
            fifeBaseRepository.addInfoServicesMotorbike(listInfo)
        }
    }
}