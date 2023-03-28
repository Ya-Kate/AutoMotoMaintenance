package com.example.automotomaintenance.ui.moto.noteService

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class NoteServiceMotoViewModel @Inject constructor() : ViewModel(),
    FifeBaseRepository.ListenerServiceMotorBike {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val noteServiceMotorBike = MutableLiveData<ArrayList<Service>>()
    private val onServiceMotorBikeUpdated = MutableLiveData<Boolean>()

    val validFail = MutableLiveData<Boolean>()
    val updateServiceSuccess = MutableLiveData<Boolean>()

    fun addServiceMotorBikeListener() {
        fifeBaseRepository.addServiceMotorBikeListener(this)
    }

    fun removeServiceMotorBikeListener() {
        fifeBaseRepository.removeServiceMotorBikeListener(this)
    }

    fun loadNoteService(idMoto: String, idService: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val serviceInfo = fifeBaseRepository.loadOneServiceMotorBike(idMoto, idService)

            withContext(Dispatchers.Main) {
                noteServiceMotorBike.postValue(serviceInfo)
            }
        }
    }

    fun validUpdateService(
        km: Int,
        data: Date,
        service: String,
        cost: String,
        idCar: String,
        idServiceCar: String
    ) {

        if (cost.isBlank() || service.isBlank()) {
            validFail.postValue(false)
        } else {
            fifeBaseRepository.updateServiceMotorBike(
                km, data, service, cost, idCar, idServiceCar
            )
            updateServiceSuccess.postValue(true)
        }
    }

    override fun onServiceMotorBikeUpdate(updateServiceMotorBike: Service) {
        onServiceMotorBikeUpdated.postValue(true)
    }

}