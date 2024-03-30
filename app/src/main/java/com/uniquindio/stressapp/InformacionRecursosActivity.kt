package com.uniquindio.stressapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.Toast
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_informacion_recursos.*
import kotlinx.coroutines.delay

class InformacionRecursosActivity : AppCompatActivity() {


    lateinit var runnable: Runnable
    lateinit var handler:Handler
    private var audio="n.a"

    var mediaPlayer:MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_recursos)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val usuario= intent.getSerializableExtra("usuario") as Usuario

        val tipo= intent.getStringExtra("tipo")

        if (tipo != null) {
            seleccionRecursos(tipo)
            audio=tipo
        }else{
            Toast.makeText(this, "error a la hora de indentificar el recurso", Toast.LENGTH_LONG).show()
        }

        buttonExercises.setOnClickListener{
            when(tipo) {
                "Actividad Física" -> {startActivity(
                    Intent(this, EjerciciosActividadFisicaActivity::class.java).putExtra("usuario",usuario))}

                "Musicoterapia" ->{startActivity(
                    Intent(this, EjerciciosMusicoterapiaActivity::class.java).putExtra("usuario",usuario))}

                "Técnicas de Respiración" ->{startActivity(
                    Intent(this, EjerciciosRespiracionActivity::class.java).putExtra("usuario",usuario))}

                "Meditación" ->{startActivity(
                    Intent(this, EjerciciosMeditacionActivity::class.java).putExtra("usuario",usuario))}
                else -> {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                }
            }
        }


        btnPlay.setOnClickListener{

            if(mediaPlayer!=null){
                if(!mediaPlayer!!.isPlaying){
                    mediaPlayer!!.start()
                    btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
                }else{
                    mediaPlayer!!.pause()
                    btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                }
            }
        }

        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
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
            btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbar.progress=0
            mediaPlayer!!.release()
            mediaPlayer=null
        }
    }

    override fun onStart() {
        super.onStart()
            if(mediaPlayer==null && !(audio.equals("n.a")) ){
                seleccionAudios(audio)
            }

        mediaPlayer!!.setOnCompletionListener {
            btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbar.progress=0
        }

        handler=Handler()
        runnable= Runnable {
            if(mediaPlayer!=null){
                seekbar.progress=mediaPlayer!!.currentPosition
                handler.postDelayed(runnable,1000)
            }
        }
        handler.postDelayed(runnable,1000)
    }


    private fun seleccionRecursos(recurso:String){

            when (recurso){
                "Actividad Física" ->{
                    //audio
                    mediaPlayer= MediaPlayer.create(this,R.raw.impaf)
                    seekbar.progress=0
                    seekbar.max= mediaPlayer!!.duration

                    //text
                    textView1ir.text="Actividad Física"
                    textView3ir.text= getString(R.string.ActiFiImp)
                    textView5ir.text= getString(R.string.ActiFiBen)
                }
                "Musicoterapia" ->{
                    mediaPlayer= MediaPlayer.create(this,R.raw.impmu)
                    seekbar.progress=0
                    seekbar.max= mediaPlayer!!.duration


                    textView1ir.text="Musicoterapia"
                    textView3ir.text= getString(R.string.ActiMuImp)
                    textView5ir.text= getString(R.string.ActiMuBen)

                }
                "Técnicas de Respiración" ->{
                    mediaPlayer= MediaPlayer.create(this,R.raw.impres)
                    seekbar.progress=0
                    seekbar.max= mediaPlayer!!.duration


                    textView1ir.text="Respiración"
                    textView3ir.text= getString(R.string.ActiResImp)
                    textView5ir.text= getString(R.string.ActiResBen)

                }
                "Meditación" ->{
                    mediaPlayer= MediaPlayer.create(this,R.raw.impme)
                    seekbar.progress=0
                    seekbar.max= mediaPlayer!!.duration


                    textView1ir.text="Meditación"
                    textView3ir.text= getString(R.string.ActiMeImp)
                    textView5ir.text= getString(R.string.ActiMeBen)

                }
                else ->{
                    Toast.makeText(this, "Error al cargar la informacion", Toast.LENGTH_LONG).show()
                }

            }
        }

    private fun seleccionAudios(recurso:String){

        when (recurso){
            "Actividad Física" ->{
                mediaPlayer= MediaPlayer.create(this,R.raw.impaf)
                seekbar.progress=0
                seekbar.max= mediaPlayer!!.duration
            }
            "Musicoterapia" ->{
                mediaPlayer= MediaPlayer.create(this,R.raw.impmu)
                seekbar.progress=0
                seekbar.max= mediaPlayer!!.duration
            }
            "Técnicas de Respiración" ->{
                mediaPlayer= MediaPlayer.create(this,R.raw.impres)
                seekbar.progress=0
                seekbar.max= mediaPlayer!!.duration

            }
            "Meditación" ->{
                mediaPlayer= MediaPlayer.create(this,R.raw.impme)
                seekbar.progress=0
                seekbar.max= mediaPlayer!!.duration

            }
            else ->{
                Toast.makeText(this, "Error al cargar la informacion", Toast.LENGTH_LONG).show()
            }

        }
    }

}