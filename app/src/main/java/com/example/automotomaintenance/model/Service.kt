package com.example.automotomaintenance.model

import android.provider.ContactsContract.Data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date

@Entity
data class Service(
    @ColumnInfo(name = "km")
    var km: Int = 0,

    @ColumnInfo(name = "data")
    var data: Date = Date(),

    @ColumnInfo(name = "service")
    var service: String = "",

    @ColumnInfo(name = "cost")
    var cost: String = "",

    @PrimaryKey(autoGenerate = true)
    var idUser: String = ""
)
