package com.example.automotomaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Service(
    @ColumnInfo(name = "km")
    val km: Int = 0,

    @ColumnInfo(name = "data")
    val data: Date = Date(),

    @ColumnInfo(name = "service")
    val service: String = "",

    @ColumnInfo(name = "cost")
    val cost: String = "",

    @ColumnInfo(name = "id")
    val idService: String = "",

    @PrimaryKey(autoGenerate = true)
    val idUser: String = ""
)

