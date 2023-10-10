package com.example.automotomaintenance.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.automotomaintenance.R
import com.example.automotomaintenance.ui.company.addcompany.AddCompanyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCompaniesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_companies)

        supportFragmentManager.beginTransaction()
            .replace(R.id.add_company_container, AddCompanyFragment())
            .commit()
    }
}