package com.example.automotomaintenance.repository

import com.example.automotomaintenance.model.Company
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.model.Vehicle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FifeBaseRepository @Inject constructor(
) {

    private val db: DatabaseReference = Firebase.database.reference

    fun addCar(brand: String, number: String, year: String, volume: String) {
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("car")
            .child(number)
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
            .child("motorbike")
            .child(number)
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

    fun getOneCar(numberAuto: String): List<Vehicle> {
        val autoInfo = arrayListOf<Vehicle>()
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("car")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        (it.getValue(Vehicle::class.java))?.let { vehicle ->
                            if (vehicle.number == numberAuto) {
                                autoInfo.add(vehicle)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                }
            })
        return autoInfo
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

    fun getOneMotorbike(numberMoto: String): List<Vehicle> {
        val motoInfo = arrayListOf<Vehicle>()
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("motorbike")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        (it.getValue(Vehicle::class.java))?.let { vehicle ->
                            if (vehicle.number == numberMoto) {
                                motoInfo.add(vehicle)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                }
            })
        return motoInfo
    }

    fun addServiceCar(km: Int, data: Date, service: String, cost: Int, number: String) {

        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("car")
            .child(number)
            .child("service")
            .child(UUID.randomUUID().toString())
            .setValue(
                Service(
                    km, data, service, cost, Firebase.auth.currentUser?.uid ?: "",
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

    fun addServiceMotorBike(km: Int, data: Date, service: String, cost: Int, number: String) {

        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("motorbike")
            .child(number)
            .child("service")
            .child(UUID.randomUUID().toString())
            .setValue(
                Service(
                    km, data, service, cost, Firebase.auth.currentUser?.uid ?: "",
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

    fun getServiceCar(number: String): ArrayList<Service> {
        val list = arrayListOf<Service>()
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("car")
            .child(number)
            .child("service")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        (it.getValue(Service::class.java))?.let { service ->
                            list.add(service)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                }
            })
        return list
    }

    fun getServiceMotorBike(number: String): ArrayList<Service> {
        val list = arrayListOf<Service>()
        db.child("vehicle")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child("motorbike")
            .child(number)
            .child("service")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        (it.getValue(Service::class.java))?.let { service ->
                            list.add(service)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                }
            })
        return list
    }

    fun addCompany(
        name: String, information: String, phone: String, person: String, address: String
    ) {
        db.child("company")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .child(UUID.randomUUID().toString())
            .setValue(
                Company(
                    name, information, phone, person, address, Firebase.auth.currentUser?.uid ?: "",
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

    fun getCompany(onChange: (list: ArrayList<Company>) -> Unit) {
        db.child("company")
            .child(Firebase.auth.currentUser?.uid ?: "")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Company>()
                    snapshot.children.forEach {
                        (it.getValue(Company::class.java))?.let { company ->
                            list.add(company)
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