package com.example.automotomaintenance.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.TransportVehicle
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListTransportViewModel @Inject constructor(
    private val fifeBaseRepository: FifeBaseRepository
) : ViewModel() {

    val listCars = MutableLiveData<List<TransportVehicle>>()
    val listMotorBikes = MutableLiveData<List<TransportVehicle>>()
    fun loadListCars() {
        viewModelScope.launch(Dispatchers.IO) {
            val cars = fifeBaseRepository.loadCars()

            withContext(Dispatchers.Main) {
                listCars.postValue(cars)
            }
        }
    }

    fun loadListMotorBikes() {
        viewModelScope.launch(Dispatchers.IO) {
            val motorBike = fifeBaseRepository.loadMotorBikes()

            withContext(Dispatchers.Main) {
                listMotorBikes.postValue(motorBike)
            }
        }
    }

    fun deleteCar(idCar: String) {
        fifeBaseRepository.deleteOneCar(idCar)
    }

    fun deleteMotorBike(idMotorBike: String) {
        fifeBaseRepository.deleteOneMotorbike(idMotorBike)
    }
}


