package com.uniquindio.stressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_fuentes.*
import kotlinx.android.synthetic.main.activity_informacion_recursos.*

class FuentesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuentes)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        llenarFuentes()

    }

    private fun llenarFuentes(){
        titleFont.text="Fuentes de Recurso Actividad Física"
        fontsContent.text= getString(R.string.ActiFiFont)
        titleFont1.text="Fuentes de Recurso Musicoterapia"
        fontsContent1.text= getString(R.string.ActiMuFont)
        titleFont2.text="Fuentes de Recurso Tecnicas de Respiración"
        fontsContent2.text= getString(R.string.ActiResFont)
        titleFont3.text="Fuentes de Recurso Meditación"
        fontsContent3.text= getString(R.string.ActiMeFont)
        titleFont4.text="Fuentes de Encuesta"
        fontsContent4.text= getString(R.string.Encu)
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}