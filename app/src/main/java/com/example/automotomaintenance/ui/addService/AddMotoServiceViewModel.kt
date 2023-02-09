package com.example.automotomaintenance.ui.addService

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddMotoServiceViewModel @Inject constructor(private val repository: FifeBaseRepository) :
    ViewModel() {

    private var motoServiceAdded: (() -> Unit)? = null

    fun addMotoService(km: Int, data: Date, service: String, cost: Int, number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addServiceMotorBike(km, data, service, cost, number)
            motoServiceAdded?.invoke()
        }
    }
}