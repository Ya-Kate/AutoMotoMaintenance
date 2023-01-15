package com.example.automotomaintenance.repository

import com.example.automotomaintenance.model.Vehicle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.UUID

class FifeBaseRepository {

    private val db = Firebase.database.reference

    fun addCar(brand: String, number: String, year: String, volume: String) {
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("car").child(UUID.randomUUID().toString())
            .setValue(
                Vehicle(
                    brand, number, year, volume, Firebase.auth.currentUser?.uid ?: ""
                )
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    //
                } else {
                    it.exception.let {
                        //
                    }
                }
            }
    }

    fun addMotorBike(brand: String, number: String, year: String, volume: String) {
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("motorbike").child(UUID.randomUUID().toString())
            .setValue(
                Vehicle(
                    brand, number, year, volume, Firebase.auth.currentUser?.uid ?: ""
                )
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    //
                } else {
                    it.exception.let {
                        //
                    }
                }
            }
    }

    fun getCars(onChange: (list: ArrayList<Vehicle>) -> Unit) {
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("car")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Vehicle>()
                    snapshot.children.forEach {
                        (it.getValue(Vehicle::class.java))?.let { vehicle ->
                            list.add(vehicle)
                        }
                    }
                    onChange(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                }
            })
    }

    fun getMotorBike(onChange: (list: ArrayList<Vehicle>) -> Unit) {
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("motorbike")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Vehicle>()
                    snapshot.children.forEach {
                        (it.getValue(Vehicle::class.java))?.let { vehicle ->
                            list.add(vehicle)
                        }
                    }
                    onChange(list)
                }
                override fun onCancelled(error: DatabaseError) {
                    //
                }
            })
    }
}