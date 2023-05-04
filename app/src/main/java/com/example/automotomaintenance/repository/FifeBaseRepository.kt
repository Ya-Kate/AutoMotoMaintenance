package com.example.automotomaintenance.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.automotomaintenance.constans.DbConstants
import com.example.automotomaintenance.model.Company
import com.example.automotomaintenance.model.InformationDB
import com.example.automotomaintenance.model.Service
import com.example.automotomaintenance.model.TransportVehicle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class FifeBaseRepository @Inject constructor(
) {
    interface Listener {
        fun onCompanyUpdated(company: Company)
    }

    interface ListenerServiceCar {
        fun onServiceCarUpdated(updateServiceCar: Service)
    }

    interface ListenerServiceMotorBike {
        fun onServiceMotorBikeUpdate(updateServiceMotorBike: Service)
    }

    private val companyUpdatedListeners: MutableList<Listener> = mutableListOf()
    private val serviceCarUpdateListeners: MutableList<ListenerServiceCar> = mutableListOf()
    private val serviceMotorBikeUpdateListeners: MutableList<ListenerServiceMotorBike> =
        mutableListOf()
    private val db: DatabaseReference = Firebase.database.reference

    fun addCompanyListener(listener: Listener) {
        companyUpdatedListeners.add(listener)
    }

    fun removeCompanyListener(listener: Listener) {
        companyUpdatedListeners.remove(listener)
    }

    fun addServiceCarListener(listener: ListenerServiceCar) {
        serviceCarUpdateListeners.add(listener)
    }

    fun removeServiceCarListener(listener: ListenerServiceCar) {
        serviceCarUpdateListeners.remove(listener)
    }

    fun addServiceMotorBikeListener(listener: ListenerServiceMotorBike) {
        serviceMotorBikeUpdateListeners.add(listener)
    }

    fun removeServiceMotorBikeListener(listener: ListenerServiceMotorBike) {
        serviceMotorBikeUpdateListeners.remove(listener)
    }

    fun addCar(brand: String, number: String, year: String, volume: String) {
        val idCar = UUID.randomUUID().toString()
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.CAR)
            .child(idCar)
            .setValue(
                TransportVehicle(
                    brand, number, year, volume, idCar, Firebase.auth.currentUser?.uid ?: ""
                )
            ).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Exception add car", task.exception)
                }
            }
    }

    fun addMotorBike(brand: String, number: String, year: String, volume: String) {
        val idMotorBike = UUID.randomUUID().toString()
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.MOTOR_BIKE)
            .child(idMotorBike)
            .setValue(
                TransportVehicle(
                    brand, number, year, volume, idMotorBike, Firebase.auth.currentUser?.uid ?: ""
                )
            ).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Exception add motorbike", task.exception)
                }
            }
    }

    suspend fun loadCars(): List<TransportVehicle> {
        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.CAR)
                .get()
                .await()
        val cars: List<TransportVehicle> = snapshot.children.map {
            it.getValue(TransportVehicle::class.java)!!
        }
        return cars
    }

    suspend fun loadOneCar(idCar: String): ArrayList<TransportVehicle> {
        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.CAR)
                .get()
                .await()

        val autoInfo = arrayListOf<TransportVehicle>()
        snapshot.children.forEach {
            (it.getValue(TransportVehicle::class.java))?.let { car ->
                if (car.id == idCar) {
                    autoInfo.add(car)
                }
            }
        }
        return autoInfo
    }

    suspend fun loadMotorBikes(): List<TransportVehicle> {
        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.MOTOR_BIKE)
                .get()
                .await()

        val motorBikes: List<TransportVehicle> = snapshot.children.map {
            it.getValue(TransportVehicle::class.java)!!
        }
        return motorBikes
    }

    suspend fun loadOneMotorbike(idMoto: String): List<TransportVehicle> {
        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.MOTOR_BIKE)
                .get()
                .await()

        val motorBikeInfo = arrayListOf<TransportVehicle>()
        snapshot.children.forEach {
            (it.getValue(TransportVehicle::class.java))?.let { motorBike ->
                if (motorBike.id == idMoto) {
                    motorBikeInfo.add(motorBike)
                }
            }
        }
        return motorBikeInfo
    }

    fun addServiceCar(km: Int, data: Date, service: String, cost: String, idCar: String) {
        val kay = db.push()
        val kayNew = kay.key.toString()
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.CAR)
            .child(idCar)
            .child(DbConstants.SERVICE)
            .child(kayNew)
            .setValue(
                Service(
                    km, data, service, cost, kayNew, Firebase.auth.currentUser?.uid ?: "",
                )
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.i(TAG, "Successful add car service.")
                } else {
                    it.exception.let { exception ->
                        Log.e(TAG, "Failed to add value car service.", exception)
                    }
                }
            }
    }

    fun addServiceMotorBike(km: Int, data: Date, service: String, cost: String, idMoto: String) {
        val kay = db.push()
        val kayNew = kay.key.toString()
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.MOTOR_BIKE)
            .child(idMoto)
            .child(DbConstants.SERVICE)
            .child(kayNew)
            .setValue(
                Service(
                    km, data, service, cost, kayNew, Firebase.auth.currentUser?.uid ?: "",
                )
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.i(TAG, "Successful add motorbike service.")
                } else {
                    it.exception.let { exception ->
                        Log.e(TAG, "Failed to add value motorbike service.", exception)
                    }
                }
            }
    }

    suspend fun loadServicesCar(idCar: String): List<Service> {

        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.CAR)
                .child(idCar)
                .child(DbConstants.SERVICE)
                .get()
                .await()

        val services: List<Service> = snapshot.children.map {
            it.getValue(Service::class.java)!!
        }
        return services
    }

    suspend fun loadOneServiceCar(idCar: String, idService: String): ArrayList<Service> {

        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.CAR)
                .child(idCar)
                .child(DbConstants.SERVICE)
                .get()
                .await()

        val serviceInfo = arrayListOf<Service>()
        snapshot.children.forEach {
            (it.getValue(Service::class.java))?.let { service ->
                if (service.idService == idService) {
                    serviceInfo.add(service)
                }
            }
        }
        return serviceInfo
    }

    fun updateServiceCar(
        km: Int,
        data: Date,
        service: String,
        cost: String,
        idCar: String,
        idServiceCar: String
    ) {
        val serviceCar = Service(
            km, data, service, cost, idServiceCar, Firebase.auth.currentUser?.uid ?: ""
        )
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.CAR)
            .child(idCar)
            .child(DbConstants.SERVICE)
            .child(idServiceCar)
            .setValue(serviceCar)

        serviceCarUpdateListeners.forEach { listenerService ->
            listenerService.onServiceCarUpdated(serviceCar)
        }
    }


    suspend fun loadServicesMotorBike(idMoto: String): List<Service> {

        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.MOTOR_BIKE)
                .child(idMoto)
                .child(DbConstants.SERVICE)
                .orderByKey()
                .get()
                .await()

        val services: List<Service> = snapshot.children.map {
            it.getValue(Service::class.java)!!
        }
        return services
    }

    suspend fun loadOneServiceMotorBike(idMoto: String, idService: String): ArrayList<Service> {

        val snapshot =
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.TRANSPORT_VEHICLE)
                .child(DbConstants.MOTOR_BIKE)
                .child(idMoto)
                .child(DbConstants.SERVICE)
                .get()
                .await()

        val serviceInfo = arrayListOf<Service>()
        snapshot.children.forEach {
            (it.getValue(Service::class.java))?.let { service ->
                if (service.idService == idService) {
                    serviceInfo.add(service)
                }
            }
        }
        return serviceInfo
    }

    fun updateServiceMotorBike(
        km: Int,
        data: Date,
        service: String,
        cost: String,
        idMoto: String,
        idService: String
    ) {
        val serviceMoto = Service(
            km, data, service, cost, idService, Firebase.auth.currentUser?.uid ?: ""
        )
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.MOTOR_BIKE)
            .child(idMoto)
            .child(DbConstants.SERVICE)
            .child(idService)
            .setValue(serviceMoto)

        serviceMotorBikeUpdateListeners.forEach { listenerService ->
            listenerService.onServiceMotorBikeUpdate(serviceMoto)
        }
    }

    fun addCompany(
        name: String, information: String, phone: String, person: String, address: String
    ) {
        val idCompany = UUID.randomUUID().toString()
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.COMPANY)
            .child(idCompany)
            .setValue(
                Company(
                    name, information, phone, person, address, idCompany,
                    Firebase.auth.currentUser?.uid ?: "",
                )
            ).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Exception aad company.", task.exception)
                }
            }
    }

    suspend fun loadCompanies(): List<Company> {
        val snapshot = db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.COMPANY)
            .get()
            .await()

        val companies: List<Company> = snapshot.children.map {
            it.getValue(Company::class.java)!!
        }
        return companies
    }

    suspend fun loadOneCompany(idCompany: String): List<Company> {

        val snapshot = db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.COMPANY)
            .get()
            .await()

        val companyInfo = arrayListOf<Company>()
        snapshot.children.forEach {
            (it.getValue(Company::class.java))?.let { company ->
                if (company.id == idCompany) {
                    companyInfo.add(company)
                }
            }
        }
        return companyInfo
    }

    fun updateCompany(
        idCompany: String,
        nameCompany: String?,
        information: String?,
        phone: String?,
        person: String?,
        address: String?
    ) {
        val company = Company(
            nameCompany.toString(),
            information.toString(),
            phone.toString(),
            person.orEmpty(),
            address.orEmpty(),
            idCompany,
            Firebase.auth.currentUser?.uid ?: ""
        )
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.COMPANY)
            .child(idCompany)
            .setValue(company)
        companyUpdatedListeners.forEach { listener ->
            listener.onCompanyUpdated(company)
        }
    }

    fun deleteCompany(nameCompany: String) {
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.COMPANY)
            .child(nameCompany)
            .removeValue()
    }

    fun deleteOneCar(idCar: String) {
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.CAR)
            .child(idCar)
            .removeValue()
    }

    fun deleteOneMotorbike(idMoto: String) {
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.MOTOR_BIKE)
            .child(idMoto)
            .removeValue()
    }

    fun deleteServiceCar(idService: String, idCar: String) {
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.CAR)
            .child(idCar)
            .child(DbConstants.SERVICE)
            .child(idService)
            .removeValue()
    }

    fun deleteServiceMotorBike(idService: String, idMoto: String) {
        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.TRANSPORT_VEHICLE)
            .child(DbConstants.MOTOR_BIKE)
            .child(idMoto)
            .child(DbConstants.SERVICE)
            .child(idService)
            .removeValue()
    }

    fun addInfoServices(
        listInfo: List<InformationDB>
    ) {
        for (i in listInfo.indices) {
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.INFO_SERVICE)
                .child(DbConstants.CAR)
                .child(listInfo[i].id)
                .setValue(
                    listInfo[i]
                ).addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "Exception aad company.", task.exception)
                    }
                }
        }
    }
    fun addInfoServicesMotorbike(
        listInfo: List<InformationDB>
    ) {
        for (i in listInfo.indices) {
            db.child(Firebase.auth.currentUser?.uid ?: "")
                .child(DbConstants.INFO_SERVICE)
                .child(DbConstants.MOTOR_BIKE)
                .child(listInfo[i].id)
                .setValue(
                    listInfo[i]
                ).addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "Exception aad company.", task.exception)
                    }
                }
        }
    }



    suspend fun loadInfoServices(): List<InformationDB> {
        val snapshot = db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.INFO_SERVICE)
            .child(DbConstants.CAR)
            .get()
            .await()

        val informationServices: List<InformationDB> = snapshot.children.map {
            it.getValue(InformationDB::class.java)!!
        }
        return informationServices
    }

    suspend fun loadInfoMotorbikeServices(): List<InformationDB> {
        val snapshot = db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.INFO_SERVICE)
            .child(DbConstants.MOTOR_BIKE)
            .get()
            .await()

        val informationServicesMotorbike: List<InformationDB> = snapshot.children.map {
            it.getValue(InformationDB::class.java)!!
        }
        return informationServicesMotorbike
    }

    suspend fun loadOneInfoService(idService: String): List<InformationDB> {

        val snapshot = db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.INFO_SERVICE)
            .child(DbConstants.CAR)
            .get()
            .await()

        val serviceInfo = arrayListOf<InformationDB>()
        snapshot.children.forEach {
            (it.getValue(InformationDB::class.java))?.let { info ->
                if (info.id == idService) {
                    serviceInfo.add(info)
                }
            }
        }
        return serviceInfo
    }

    suspend fun loadOneInfoServiceMotorbike(idService: String): List<InformationDB> {

        val snapshot = db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.INFO_SERVICE)
            .child(DbConstants.MOTOR_BIKE)
            .get()
            .await()

        val serviceInfo = arrayListOf<InformationDB>()
        snapshot.children.forEach {
            (it.getValue(InformationDB::class.java))?.let { info ->
                if (info.id == idService) {
                    serviceInfo.add(info)
                }
            }
        }
        return serviceInfo
    }

    fun upDateInfoService(km: String, idService: String) {

        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.INFO_SERVICE)
            .child(DbConstants.CAR)
            .child(idService)
            .updateChildren(
                mapOf(
                    "intervalKM" to km
                )
            )
    }

    fun upDateInfoServiceMotorbike(km: String, idService: String) {

        db.child(Firebase.auth.currentUser?.uid ?: "")
            .child(DbConstants.INFO_SERVICE)
            .child(DbConstants.MOTOR_BIKE)
            .child(idService)
            .updateChildren(
                mapOf(
                    "intervalKM" to km
                )
            )
    }
}