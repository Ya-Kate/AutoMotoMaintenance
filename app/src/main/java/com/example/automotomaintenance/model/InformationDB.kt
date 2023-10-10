package com.example.automotomaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.automotomaintenance.constans.DbConstants

@Entity(tableName = DbConstants.INFO_SERVICE)
data class InformationDB(
    @ColumnInfo(name = "nameWork")
    val nameWork: String = "",

    @ColumnInfo(name = "intervalKM")
    val intervalKM: String = "",

    @ColumnInfo(name = "id")
    val id: String = "",

    @PrimaryKey(autoGenerate = true)
    val idDB: Int = 0
)