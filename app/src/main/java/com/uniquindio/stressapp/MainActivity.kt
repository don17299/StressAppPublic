package com.uniquindio.stressapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        //comment
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkConn()

        buttonLogin.setOnClickListener {
                val usuario = obtenerCampos()
                if (usuario != null) {
                    startActivity(
                        Intent(this, EncuestaUnoActivity::class.java).putExtra(
                            "usuario",
                            usuario
                        )
                    )
                }

        }

        buttonLogin2.setOnClickListener {
            startActivity(Intent(this, ReportesActivity::class.java))
        }
    }


    private fun checkConn(){
        val manager= applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo= manager.activeNetworkInfo

        if(networkInfo==null){
            Toast.makeText(this, "No hay acceso a internet",Toast.LENGTH_LONG).show()
            finish()
        }
    }


    private fun obtenerCampos(): Usuario? {
        val nombre = InputNombre.text.toString()
        val correo = InputCorreo.text.toString()
        val programa = spinner.selectedItem.toString()
        var usuario:Usuario?=null

        if(nombre!="" && correo!="" && programa!="Seleccione su Programa"){
            if(correo.contains("@uqvirtual.edu.co")){
                validarUsuario(correo)

                usuario= Usuario(nombre, correo, programa)
            }else{
                Toast.makeText(this, "Debe Ingresar Un correo Estudiantil de la Universidad del Quindio",Toast.LENGTH_LONG).show()
            }
        }else{
        Toast.makeText(this, "Debe Ingresar Todos los campos",Toast.LENGTH_LONG).show()
        }

        return usuario
    }

    private fun validarUsuario(correo: String){
        val userQuery= db.collection("usuarios").whereEqualTo("correo",correo).get()

        userQuery.addOnSuccessListener {document->
            if(!(document.isEmpty)){
                Toast.makeText(this, "El correo ingresado ya se encuentra registrado",Toast.LENGTH_LONG).show()

                }
        }

    }

}
