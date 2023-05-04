package com.example.automotomaintenance.ui.information.db

import android.content.Context
import androidx.room.*
import com.example.automotomaintenance.model.InformationDB

@Database(entities = [InformationDB::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun informationDao(): InformationDao

    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java, "user.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}