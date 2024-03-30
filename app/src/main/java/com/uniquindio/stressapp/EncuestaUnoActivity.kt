package com.uniquindio.stressapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.encuesta.*

class EncuestaUnoActivity : AppCompatActivity() {

    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.encuesta)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btnContinuar.setOnClickListener {
            val en1 = encuesta()
            if (en1 != null) {
                usuario.encuesta1 = en1
                finishAffinity()
                startActivity(
                    Intent(this, MenuRecursosActivity::class.java).putExtra(
                        "usuario",
                        usuario
                    ).putExtra("puntaje", obtenerPuntaje(usuario)))
            }else{
                Toast.makeText(this, "Debe responder a todas las preguntas", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun obtenerPuntaje(usuario: Usuario):Int{
        var puntaje=0

        for (i in 0..((usuario.encuesta1.size)-1)){
            puntaje+=usuario.encuesta1.get(i)
        }

        return puntaje
    }

    private fun encuesta(): ArrayList<Int>?{
        val encuesta:ArrayList<Int> = ArrayList()
        val opciones:MutableList<String> =ArrayList()

        opciones.add(opcionesP1.selectedItem.toString())
        opciones.add(opcionesP2.selectedItem.toString())
        opciones.add(opcionesP3.selectedItem.toString())
        opciones.add(opcionesP4.selectedItem.toString())
        opciones.add(opcionesP5.selectedItem.toString())
        opciones.add(opcionesP6.selectedItem.toString())
        opciones.add(opcionesP7.selectedItem.toString())
        opciones.add(opcionesP8.selectedItem.toString())
        opciones.add(opcionesP9.selectedItem.toString())
        opciones.add(opcionesP10.selectedItem.toString())

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
}