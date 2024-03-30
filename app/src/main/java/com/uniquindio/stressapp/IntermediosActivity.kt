package com.uniquindio.stressapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_intermedios.*
import kotlinx.android.synthetic.main.activity_novatos.*

class IntermediosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermedios)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btnAFI.setOnClickListener {
            startActivity(Intent(this, MenuRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("ExerciseDone",true))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}