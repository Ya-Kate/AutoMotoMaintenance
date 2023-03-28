package com.example.automotomaintenance.ui.moto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.model.TransportVehicle
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MotoViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val infoOneMotorBike = MutableLiveData<List<TransportVehicle>>()
    val motoServices = MutableLiveData<List<Service>>()

    fun loadOneMotorbike(idMoto: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val motoInfo = fifeBaseRepository.loadOneMotorbike(idMoto)

            withContext(Dispatchers.Main) {
                infoOneMotorBike.postValue(motoInfo)
            }
        }
    }

    fun loadServicesMotorBike(idMoto: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val services = fifeBaseRepository.loadServicesMotorBike(idMoto)

            withContext(Dispatchers.Main) {
                motoServices.postValue(services)
            }
        }
    }
}