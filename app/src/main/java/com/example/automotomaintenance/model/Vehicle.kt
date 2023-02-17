package com.example.automotomaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(
    @ColumnInfo(name = "brand")
    var brand: String = "",

    @ColumnInfo(name = "number")
    var number: String = "",

    @ColumnInfo(name = "year")
    var year: String = "",

    @ColumnInfo(name = "volume")
    var volume: String = "",

    @PrimaryKey(autoGenerate = true)
    var idUser: String = ""
)
