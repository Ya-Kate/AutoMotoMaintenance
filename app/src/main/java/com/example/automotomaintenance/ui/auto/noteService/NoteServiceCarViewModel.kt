package com.example.automotomaintenance.ui.auto.noteService

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteServiceCarViewModel @Inject constructor() : ViewModel(),
    FifeBaseRepository.ListenerServiceCar {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val noteServiceCar = MutableLiveData<ArrayList<Service>>()
    private val onServiceCarUpdated = MutableLiveData<Boolean>()

    val validFail = MutableLiveData<Boolean>()
    val updateServiceSuccess = MutableLiveData<Boolean>()

    fun addServiceCarListener() {
        fifeBaseRepository.addServiceCarListener(this)
    }

    fun removeServiceCarListener() {
        fifeBaseRepository.removeServiceCarListener(this)
    }

    fun loadNoteService(idCar: String, idCompany: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val serviceInfo = fifeBaseRepository.
            loadOneServiceCar(idCar, idCompany)

            withContext(Dispatchers.Main) {
                noteServiceCar.postValue(serviceInfo)
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
            fifeBaseRepository.updateServiceCar(
                km, data, service, cost, idCar, idServiceCar
            )
            updateServiceSuccess.postValue(true)
        }
    }

    override fun onServiceCarUpdated(updateServiceCar: Service) {
        onServiceCarUpdated.postValue(true)
    }
}