package com.uniquindio.stressapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_ejercicios_actividad_fisica.*
import kotlinx.android.synthetic.main.activity_ejercicios_meditacion.*
import kotlinx.android.synthetic.main.activity_ejercicios_meditacion.btnMe

class EjerciciosActividadFisicaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_actividad_fisica)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btnAFAn.setOnClickListener {
            startActivity(Intent(this, NovatosActivity ::class.java).putExtra("usuario",usuario))
        }
        btnAFAi.setOnClickListener {
            startActivity(Intent(this, IntermediosActivity::class.java).putExtra("usuario",usuario))
        }
        btnAFAa.setOnClickListener {
            startActivity(Intent(this, AvanzadosActivity::class.java).putExtra("usuario",usuario))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}