package com.example.automotomaintenance.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject



class AuthRepository @Inject constructor() {

    private val firebaseAuth: FirebaseAuth = Firebase.auth

    fun isUserLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    fun getUserEmail(): String {
        return firebaseAuth.currentUser?.email ?: ""
    }

    fun login(
        email: String, password: String, onSuccess: () -> Unit, onError: (e: Exception) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                task.exception?.let { onError(it) }
            }
        }
    }

    fun register(
        email: String, password: String, onSuccess: () -> Unit, onError: (e: Exception) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                task.exception?.let { onError(it) }
            }
        }
    }
}