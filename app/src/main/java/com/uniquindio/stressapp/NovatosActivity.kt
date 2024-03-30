package com.uniquindio.stressapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_ejercicios_actividad_fisica.*
import kotlinx.android.synthetic.main.activity_novatos.*

class NovatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novatos)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btnAFN.setOnClickListener {
            startActivity(Intent(this, MenuRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("ExerciseDone",true))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}