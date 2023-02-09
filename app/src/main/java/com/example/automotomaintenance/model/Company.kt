package com.example.automotomaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "information")
    var information: String = "",

    @ColumnInfo(name = "phone")
    var phone: String = "",

    @ColumnInfo(name = "human")
    var human: String = "",

    @ColumnInfo(name = "address")
    var address: String = "",

    @PrimaryKey(autoGenerate = true)
    var idUser: String = ""
)
