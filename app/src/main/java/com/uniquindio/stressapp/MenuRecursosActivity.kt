package com.uniquindio.stressapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_menu_recursos.*

class MenuRecursosActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_recursos)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        val ExerciseDone= intent.getBooleanExtra("ExerciseDone",false)

        val puntaje = intent.getIntExtra("puntaje",-1)


        if(puntaje!=-1) {
            textView3mr.text =
                "Ha obtenido un Puntaje de " + puntaje + " en una escala de estrés de 0 a 40 en la anterior encuesta"
        }

        if(ExerciseDone){
            btnEncuesta.isEnabled=true
            btnEncuesta.setBackgroundColor(Color.parseColor("#88BD1C"))
        }

        btnEncuesta.setOnClickListener {
            startActivity(Intent(this, EncuestaDosActivity::class.java).putExtra("usuario", usuario))
        }

        btnR1.setOnClickListener {
            startActivity(Intent(this, InformacionRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("tipo",btnR1.text))
        }

        btnR2.setOnClickListener {
            startActivity(Intent(this, InformacionRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("tipo",btnR2.text))
        }

        btnR3.setOnClickListener {
            startActivity(Intent(this, InformacionRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("tipo",btnR3.text))
        }

        btnR4.setOnClickListener {
            startActivity(Intent(this, InformacionRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("tipo",btnR4.text))
        }

        buttonSources.setOnClickListener {
            startActivity(Intent(this, FuentesActivity::class.java))
        }
    }
}