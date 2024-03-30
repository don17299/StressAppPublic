package com.uniquindio.stressapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_ejercicios_musicoterapia.*
import kotlinx.android.synthetic.main.activity_menu_recursos.*

class EjerciciosMusicoterapiaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_musicoterapia)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btn1M.setOnClickListener {
            startActivity(Intent(this, VideoPlayerActivity::class.java).putExtra("url","https://www.youtube.com/watch?v=2PzsMfJNFm0"))
        }

        btn2M.setOnClickListener {
            startActivity(Intent(this, VideoPlayerActivity::class.java).putExtra("url","https://www.youtube.com/watch?v=kag0aJqQsGo"))
        }

        btn3M.setOnClickListener {
            startActivity(Intent(this, VideoPlayerActivity::class.java).putExtra("url","https://www.youtube.com/watch?v=J0bBVeqlQfM"))
        }

        btn4M.setOnClickListener {
            startActivity(Intent(this, VideoPlayerActivity::class.java).putExtra("url","https://www.youtube.com/watch?v=YXnjy5YlDwk"))
        }

        btnM.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MenuRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("ExerciseDone",true))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}