package com.example.automotomaintenance.ui.auto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Vehicle
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val oneAuto = MutableLiveData<List<Vehicle>>()

    fun getOneAuto(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            oneAuto.postValue(fifeBaseRepository.getOneCar(number))
        }
    }
}