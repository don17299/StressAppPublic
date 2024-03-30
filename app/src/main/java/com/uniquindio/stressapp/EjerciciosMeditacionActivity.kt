package com.uniquindio.stressapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.uniquindio.stressapp.R
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_ejercicios_meditacion.*
import kotlinx.android.synthetic.main.activity_ejercicios_respiracion.*
import kotlinx.android.synthetic.main.activity_informacion_recursos.*

class EjerciciosMeditacionActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    lateinit var handler: Handler

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_meditacion)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btnMe.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MenuRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("ExerciseDone",true))
        }

        btnPlayMe.setOnClickListener{

            if(mediaPlayer!=null){
                if(!mediaPlayer!!.isPlaying){
                    mediaPlayer!!.start()
                    btnPlayMe.setImageResource(R.drawable.ic_baseline_pause_24)
                }else{
                    mediaPlayer!!.pause()
                    btnPlayMe.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                }
            }
        }

        seekbarMe.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(mediaPlayer!=null) {
                    if (p2) {
                        mediaPlayer!!.seekTo(p1)
                    }
                }
            }


            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStop() {
        super.onStop()
        if(mediaPlayer!=null){
            btnPlayMe.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbarMe.progress=0
            mediaPlayer!!.release()
            mediaPlayer=null
        }
    }

    override fun onStart() {
        super.onStart()
        if(mediaPlayer==null){
            mediaPlayer= MediaPlayer.create(this,R.raw.ejerme)
            seekbarMe.progress=0
            seekbarMe.max= mediaPlayer!!.duration
        }

        mediaPlayer!!.setOnCompletionListener {
            btnPlayMe.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbarMe.progress=0
        }

        handler= Handler()
        runnable= Runnable {
            if(mediaPlayer!=null){
                seekbarMe.progress=mediaPlayer!!.currentPosition
                handler.postDelayed(runnable,1000)
            }
        }
        handler.postDelayed(runnable,1000)
    }
}