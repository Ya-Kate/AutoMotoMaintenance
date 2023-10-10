package com.example.automotomaintenance.repository

import com.example.automotomaintenance.model.InformationDB
import com.example.automotomaintenance.ui.information.db.AppDataBase
import com.example.automotomaintenance.ui.information.db.InformationDao
import javax.inject.Inject
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext


class DataBaseRepository @Inject constructor(
    @ApplicationContext context: Context
) {

    var db: InformationDao = AppDataBase.getInstance(context)?.informationDao()!!

    suspend fun addListInformation(infoList: List<InformationDB>) {
        db.insertListInformation(infoList)
    }

    suspend fun addListMotorbikeInformation(infoList: List<InformationDB>) {
        db.insertListMotorbikeInformation(infoList)
    }
}