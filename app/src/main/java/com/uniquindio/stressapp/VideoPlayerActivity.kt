package com.uniquindio.stressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import com.uniquindio.stressapp.models.Usuario
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val actionBar=supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val url= intent.getStringExtra("url")
        var error=false

        vpwb1.apply {
            if (url != null) {
                loadUrl(url)
            }else{
                error=true;
            }
            webViewClient = WebViewClient()
            settings.javaScriptEnabled=true

        }
        if(error){
            Toast.makeText(this, "Ocurrio un error con la url", Toast.LENGTH_LONG).show()
            error=false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}