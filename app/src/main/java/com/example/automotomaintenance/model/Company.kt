package com.example.automotomaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "information")
    val information: String = "",

    @ColumnInfo(name = "phone")
    val phone: String = "",

    @ColumnInfo(name = "person")
    val person: String = "",

    @ColumnInfo(name = "address")
    val address: String = "",

    @ColumnInfo(name = "id")
    val id: String = "",

    @PrimaryKey(autoGenerate = true)
    val idUser: String = ""
)
