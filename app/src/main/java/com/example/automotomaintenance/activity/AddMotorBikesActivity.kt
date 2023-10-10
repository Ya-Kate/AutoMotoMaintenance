package com.example.automotomaintenance.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.automotomaintenance.R
import com.example.automotomaintenance.ui.moto.addmoto.AddMotoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMotorBikesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiti_add_motorbices)

        supportFragmentManager.beginTransaction()
            .replace(R.id.add_moto_container, AddMotoFragment())
            .commit()
    }
}