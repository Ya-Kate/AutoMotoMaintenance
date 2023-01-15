package com.example.automotomaintenance.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.automotomaintenance.R
import com.example.automotomaintenance.repository.AuthRepository


class MainActivity : AppCompatActivity() {

    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (authRepository.isUserLogin()) {
            //add vehicle
            findNavController(R.id.my_nav_host_fragment).navigate(R.id.action_startProjectFragment2_to_fragment_add_vehicle)
        } else {
            //start fragment
            findNavController(R.id.my_nav_host_fragment).navigate(R.id.action_startProjectFragment2_self)
        }
    }
}

