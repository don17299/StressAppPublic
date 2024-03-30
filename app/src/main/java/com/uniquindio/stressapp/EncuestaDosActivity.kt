package com.uniquindio.stressapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.encuesta.*
import kotlinx.android.synthetic.main.encuesta.btnContinuar
import kotlinx.android.synthetic.main.encuesta_dos.*

class EncuestaDosActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    private var error=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.encuesta_dos)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btnContinuarE2.setOnClickListener {
            val en2 = encuesta2()
            if (en2 != null) {
                val help = helpNeeded()
                if (!error) {
                    usuario.encuesta2 = en2
                    usuario.isNeedHelp = help
                    persistir(usuario)
                    Toast.makeText(this, "Datos Registrados", Toast.LENGTH_LONG).show()
                    finishAffinity()
                    startActivity(Intent(this, CalificacionActivity::class.java).putExtra("correo",usuario.correo).putExtra("puntaje", obtenerPuntaje(usuario)))
                } else {
                    error = false
                }
            }else{
                Toast.makeText(this, "Debe responder a todas las preguntas", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun obtenerPuntaje(usuario: Usuario):Int{
        var puntaje=0

        for (i in 0 until (usuario.encuesta2.size)){
            puntaje+= usuario.encuesta2[i]
        }

        return puntaje
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

     fun persistir(usuario: Usuario):Boolean{
        var verdad:Boolean=false
        val user= hashMapOf(
            "nombre" to usuario.nombre,
            "correo" to usuario.correo,
            "programa" to usuario.programa,
            "encuesta1" to usuario.encuesta1,
            "encuesta2" to usuario.encuesta2,
            "needHelp" to usuario.isNeedHelp,
            "calificacion1" to usuario.calificacion1,
            "calificacion2" to usuario.calificacion2,
            "calificacion3" to usuario.calificacion3,
            "sugerencia" to usuario.sugerencia
        )

         val userList= db.collection("usuarios")

         val query = userList.whereEqualTo("correo",usuario.correo).get()

         query.addOnSuccessListener {document->
             if(!(document.isEmpty)) {
                 for(doc in 0 until document.size()) {
                     if(doc==0) {
                         userList.document(document.documents[doc].id).set(user, SetOptions.merge())
                     }else{
                         userList.document(document.documents[doc].id).delete()
                     }
                 }
             }else{
                 userList.add(user)
             }

             verdad=true
         }


        return verdad
    }

    private fun encuesta2(): ArrayList<Int>?{
        val encuesta:ArrayList<Int> = ArrayList()
        val opciones:MutableList<String> =ArrayList()

        opciones.add(opcionesP1E2.selectedItem.toString())
        opciones.add(opcionesP2E2.selectedItem.toString())
        opciones.add(opcionesP3E2.selectedItem.toString())
        opciones.add(opcionesP4E2.selectedItem.toString())
        opciones.add(opcionesP5E2.selectedItem.toString())
        opciones.add(opcionesP6E2.selectedItem.toString())
        opciones.add(opcionesP7E2.selectedItem.toString())
        opciones.add(opcionesP8E2.selectedItem.toString())
        opciones.add(opcionesP9E2.selectedItem.toString())
        opciones.add(opcionesP10E2.selectedItem.toString())

        for(i in 0..9) {
            if(i==3||i==4||i==6||i==7) {
                when (opciones.get(i)) {
                    "Siempre" -> encuesta.add(0)
                    "Casi siempre" -> encuesta.add(1)
                    "De vez en cuando" -> encuesta.add(2)
                    "Casi nunca" -> encuesta.add(3)
                    "Nunca" -> encuesta.add(4)
                    else -> return null
                }
            }else{
                when (opciones.get(i)) {
                    "Nunca" -> encuesta.add(0)
                    "Casi nunca" -> encuesta.add(1)
                    "De vez en cuando" -> encuesta.add(2)
                    "Casi siempre" -> encuesta.add(3)
                    "Siempre" -> encuesta.add(4)
                    else -> return null
                }

            }
        }

        return encuesta
    }

    private fun helpNeeded():Boolean{

        val idB =rgHelp.checkedRadioButtonId
        val button=findViewById<RadioButton>(idB)

        if(button!=null) {
            if (button.text.equals("Si")) {
                return true
            } else {
                if (button.text.equals("No")) {
                    return false
                }
            }
        }else{
            Toast.makeText(this, "Debe especificar si necesita ayuda",Toast.LENGTH_LONG).show()
            error=true

        }

        return false
    }
}