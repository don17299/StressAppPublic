package com.uniquindio.stressapp

import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itextpdf.text.pdf.PdfWriter
import com.uniquindio.stressapp.models.Reporte
import kotlinx.android.synthetic.main.activity_reportes.*
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream


class ReportesActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    private val reporte= Reporte()

    private var contrasenia=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),PackageManager.PERMISSION_GRANTED)

        db.collection("admin").get().addOnSuccessListener { document ->
            if (!document.isEmpty) {
                for(doc in document){
                    contrasenia= doc.data["clave"].toString()
                }
            }
        }



        btnAunt.setOnClickListener {
            comprobarContrasena()
        }

        btnReport.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                inicializarReporte()

                Toast.makeText(this, "Generados", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "No se pudieron generar los reportes", Toast.LENGTH_LONG).show()
            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun comprobarContrasena(){
        val password = InputPass.text.toString().trim()

        if(password == contrasenia){
            btnReport.isEnabled=true
            btnReport.setBackgroundColor(Color.parseColor("#88BD1C"))
            Toast.makeText(this, "Autenticado", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_LONG).show()
        }
    }

    private fun inicializarReporte() {

        db.collection("usuarios").get().addOnSuccessListener { document ->
            if (!document.isEmpty) {
                for (doc in document) {
                    reporte.usuarios.add(doc.data)
                }
                textReports.text=reporte.reporte()
            }
        }
    }





}