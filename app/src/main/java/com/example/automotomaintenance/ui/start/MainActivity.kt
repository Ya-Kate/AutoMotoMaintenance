package com.example.automotomaintenance.ui.start

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.example.automotomaintenance.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        скрываем нижнюю панель
        if (Build.VERSION.SDK_INT < 19) {
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            decorView.systemUiVisibility = uiOptions
        }

//        выключили ночную тему начало
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        выключили ночную тему конец

        if (viewModel.isUserLogin()) {
            //add vehicle
            findNavController(R.id.my_nav_host_fragment).navigate(R.id.action_startProjectFragment_to_navigationMainFragment)
        }
    }
}



