package com.example.automotomaintenance.ui.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var authRepository: AuthRepository

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login(
                email.trim(),
                password.trim(),
                {
                    onSuccess()
                },
                { exception ->
                    onError(exception)
                }
            )
        }
    }

    fun register(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception) -> Unit
    ) {
        authRepository.register(
            email.trim(),
            password.trim(), {
                onSuccess()
            }, { exception ->
                onError(exception)
            })
    }
}