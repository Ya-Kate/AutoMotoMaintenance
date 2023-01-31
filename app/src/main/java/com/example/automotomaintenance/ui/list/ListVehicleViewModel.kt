package com.example.automotomaintenance.ui.list

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
class ListVehicleViewModel @Inject constructor(
    private val fifeBaseRepository: FifeBaseRepository
) : ViewModel() {

     val listAuto = MutableLiveData<ArrayList<Vehicle>>()

    fun getListAuto() {
        viewModelScope.launch(Dispatchers.IO) {
            fifeBaseRepository.getCars {
                if(it.isNotEmpty()){
                    listAuto.postValue(it)
                }
            }
        }
    }
}

