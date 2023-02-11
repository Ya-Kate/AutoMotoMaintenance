package com.example.automotomaintenance.ui.moto.addmoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMotoViewModel @Inject constructor(private val repository: FifeBaseRepository) :
    ViewModel() {

    private var motoAdded: (() -> Unit)? = null

    fun addMoto(brand: String, number: String, year: String, volume: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMotorBike(brand, number, year, volume)
            motoAdded?.invoke()
        }
    }
}