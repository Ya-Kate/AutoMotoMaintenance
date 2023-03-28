package com.example.automotomaintenance.ui.auto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Company
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.model.TransportVehicle
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AutoViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val infoOneAuto = MutableLiveData<ArrayList<TransportVehicle>>()
    val autoServices = MutableLiveData<List<Service>>()
    val infoServiceOneCar = MutableLiveData<List<Service>>()

    fun loadOneAuto(idCar: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val carInfo = fifeBaseRepository.loadOneCar(idCar)

            withContext(Dispatchers.Main) {
                infoOneAuto.postValue(carInfo)
            }
        }
    }

    fun loadAutoServices(idCar: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val carService = fifeBaseRepository.loadServicesCar(idCar)

            withContext(Dispatchers.Main) {
                autoServices.postValue(carService)
            }
        }
    }

    fun deleteServiceCar(idService: String, idCar: String) {
        fifeBaseRepository.deleteServiceCar(idService, idCar)
    }
}
