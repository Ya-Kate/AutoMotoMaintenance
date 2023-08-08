package com.example.automotomaintenance.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.automotomaintenance.R
import com.example.automotomaintenance.ui.auto.addauto.AddAutoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cars)

        supportFragmentManager.beginTransaction()
            .replace(R.id.add_moto_container, AddAutoFragment())
            .commit()
    }
}