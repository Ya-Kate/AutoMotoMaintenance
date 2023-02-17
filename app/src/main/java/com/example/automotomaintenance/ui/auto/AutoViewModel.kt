package com.example.automotomaintenance.ui.auto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.model.Vehicle
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
    val infoOneAuto = MutableLiveData<List<Vehicle>>()
    val autoService = MutableLiveData<List<Service>>()

    fun loadOneAuto(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val carInfo = fifeBaseRepository.getOneCar(number)

            withContext(Dispatchers.Main) {
                infoOneAuto.postValue(carInfo)
            }
        }
    }

    fun loadAutoService(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val carService = fifeBaseRepository.getServiceCar(number)

            withContext(Dispatchers.Main) {
                autoService.postValue(carService)
            }
        }
    }
}
