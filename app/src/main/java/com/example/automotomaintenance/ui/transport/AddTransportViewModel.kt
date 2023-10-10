package com.example.automotomaintenance.ui.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddTransportViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var authRepository: AuthRepository
    val userEmail = MutableLiveData<String>()

    fun getUserEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = authRepository.getUserEmail()

            withContext(Dispatchers.Main) {
                userEmail.postValue(email)
            }
        }
    }

    fun logout() {
        authRepository.logout()
    }
}