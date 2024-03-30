package com.uniquindio.stressapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_ejercicios_musicoterapia.*
import kotlinx.android.synthetic.main.activity_ejercicios_respiracion.*
import kotlinx.android.synthetic.main.activity_ejercicios_respiracion.audio
import kotlinx.android.synthetic.main.activity_informacion_recursos.*

class EjerciciosRespiracionActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    lateinit var handler:Handler

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_respiracion)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        btnR.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MenuRecursosActivity::class.java).putExtra("usuario",usuario).putExtra("ExerciseDone",true))
        }

        btnPlayRes.setOnClickListener{

            if(mediaPlayer!=null){
                if(!mediaPlayer!!.isPlaying){
                    mediaPlayer!!.start()
                    btnPlayRes.setImageResource(R.drawable.ic_baseline_pause_24)
                }else{
                    mediaPlayer!!.pause()
                    btnPlayRes.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                }
            }
        }

        seekbarRes.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
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
            btnPlayRes.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbarRes.progress=0
            mediaPlayer!!.release()
            mediaPlayer=null
        }
    }

    override fun onStart() {
        super.onStart()
        if(mediaPlayer==null){
            mediaPlayer= MediaPlayer.create(this,R.raw.ejerres)
            seekbarRes.progress=0
            seekbarRes.max= mediaPlayer!!.duration
        }

        mediaPlayer!!.setOnCompletionListener {
            btnPlayRes.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbarRes.progress=0
        }

        handler= Handler()
        runnable= Runnable {
            if(mediaPlayer!=null){
                seekbarRes.progress=mediaPlayer!!.currentPosition
                handler.postDelayed(runnable,1000)
            }
        }
        handler.postDelayed(runnable,1000)
    }
}