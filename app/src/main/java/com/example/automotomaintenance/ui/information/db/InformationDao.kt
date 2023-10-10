package com.example.automotomaintenance.ui.information.db

import androidx.room.Dao
import androidx.room.Update
import com.example.automotomaintenance.model.InformationDB

@Dao
interface InformationDao {

    @Update
    suspend fun insertListInformation(noteInfo: List<InformationDB>)

    @Update
    suspend fun insertListMotorbikeInformation(noteInfo: List<InformationDB>)

//    @Query("SELECT * FROM ${DbConstants.INFO_SERVICE}")
//    suspend fun selectAllInformation(): List<InformationDB>
}