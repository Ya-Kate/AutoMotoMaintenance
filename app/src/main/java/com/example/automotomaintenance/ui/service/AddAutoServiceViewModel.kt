package com.example.automotomaintenance.ui.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddAutoServiceViewModel @Inject constructor(private val repository: FifeBaseRepository) :
    ViewModel() {

    private var autoServiceAdded: (() -> Unit)? = null

    fun addAutoService(km: Int, data: Date, service: String, cost: String, number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addServiceCar(km, data, service, cost, number)
            autoServiceAdded?.invoke()
        }
    }

}