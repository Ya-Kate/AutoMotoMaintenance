package com.example.automotomaintenance.ui.information.db

import androidx.room.TypeConverter
import com.example.automotomaintenance.model.InformationDB
import com.google.gson.Gson   

class InfoConverters {

    @TypeConverter
    fun listToJson(value: List<InformationDB>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<InformationDB>::class.java).toList()
}