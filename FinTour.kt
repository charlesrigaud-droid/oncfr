package com.example.oncfr

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat




class FinTour : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContentView(R.layout.activity_fin_tour)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textaff = getString(R.string.texttour, Global1.sort)
        val text2: TextView = findViewById(R.id.text2)
        when(Global1.sort){
            1 -> textaff = "Bravo"
            2 -> textaff = "Perdu erreur Observation"
            3 -> textaff = "Temps écoulé"
            4 -> textaff = "Mort de la vie"

        }

        text2.text = textaff

        val result = findViewById<ImageView>(R.id.imgWhy)

        when(Global1.sort){
            1 -> result.setImageResource(R.drawable.win)
            2 -> result.setImageResource(R.drawable.cmd1)
            3 -> result.setImageResource(R.drawable.badtiming)
            4 -> result.setImageResource(R.drawable.defeat)

        }


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }, 2000) // Délai de 2000 millisecondes




        }
    }
