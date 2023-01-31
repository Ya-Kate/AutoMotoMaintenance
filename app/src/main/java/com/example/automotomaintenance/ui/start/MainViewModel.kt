package com.example.automotomaintenance.ui.start

import androidx.lifecycle.ViewModel
import com.example.automotomaintenance.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun isUserLogin() = authRepository.isUserLogin()
}