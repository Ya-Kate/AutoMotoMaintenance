package com.example.automotomaintenance.ui.moto

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
class MotoViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val motorbike = MutableLiveData<List<Vehicle>>()
    val motoService = MutableLiveData<List<Service>>()

    fun infoOneMotorbike(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val motoInfo = fifeBaseRepository.getOneMotorbike(number)

            withContext(Dispatchers.Main) {
                motorbike.postValue(motoInfo)
            }
        }
    }

    fun getMotoService(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val motoServices = fifeBaseRepository.getServiceMotorBike(number)

            withContext(Dispatchers.Main) {
                motoService.postValue(motoServices)
            }
        }
    }
}
