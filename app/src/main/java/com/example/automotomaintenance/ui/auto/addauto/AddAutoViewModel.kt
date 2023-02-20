package com.example.automotomaintenance.ui.auto.addauto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAutoViewModel @Inject constructor(private val repository: FifeBaseRepository) :
    ViewModel() {

    private var autoAdded: (() -> Unit)? = null

    fun addAuto(brand: String, number: String, year: String, volume: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCar(brand, number, year, volume)
            autoAdded?.invoke()
        }
    }

}
