package com.uniquindio.stressapp

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uniquindio.stressapp.models.Usuario
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StressAppTest {

    private val db = Firebase.firestore

    @Test
    fun ingresoDatos() {

        val user= Usuario("carlos","a@uqvirtual.edu.co","Enfermeria")

        db.collection("usuarios").add(user).addOnSuccessListener {
            assertNotNull(it.id)
        }
    }
}