package com.se.iths.app21.grupp1.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.se.iths.app21.grupp1.myapplication.R

class IntroScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_screen)

        Handler().postDelayed({
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}