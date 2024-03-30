package com.uniquindio.stressapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_calificacion.*
import kotlinx.android.synthetic.main.activity_menu_recursos.*

class CalificacionActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificacion)

        val correo= intent.getStringExtra("correo")

        val puntaje = intent.getIntExtra("puntaje",-1)


        if(puntaje!=-1) {
            textResults.text = "Ha obtenido un Puntaje de " + puntaje + " en una escala de estrés de 0 a 40 en la anterior encuesta"
        }

        btnEnd.setOnClickListener {
            val c1= ratingBar1.rating.toInt()
            val c2= ratingBar2.rating.toInt()
            val c3= ratingBar3.rating.toInt()
            val sugerencia= eTextS.text.toString()

            if(c1!=0 && c2!=0 && c3!=0){

                val userList= db.collection("usuarios")

                val query = userList.whereEqualTo("correo",correo).get()

                query.addOnSuccessListener {document->
                    if(!(document.isEmpty)) {
                        for(doc in 0 until document.size()) {
                            if(doc==0) {
                                userList.document(document.documents[doc].id).update("calificacion1",c1)
                                userList.document(document.documents[doc].id).update("calificacion2",c2)
                                userList.document(document.documents[doc].id).update("calificacion3",c3)
                                if(sugerencia!="") {
                                    userList.document(document.documents[doc].id).update("sugerencia", sugerencia)
                                }
                            }else{
                                userList.document(document.documents[doc].id).delete()
                            }
                        }
                    }else{
                        Toast.makeText(this, "Error de BD", Toast.LENGTH_LONG).show()
                    }
                }

                Toast.makeText(this, "Calificación Registrada", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this, MainActivity::class.java))

            }else{
                Toast.makeText(this, "Debe Calificar entre 1 y 5 todos los criterios", Toast.LENGTH_LONG).show()
            }
        }
    }
}