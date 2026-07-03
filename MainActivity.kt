package com.example.oncfr


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.util.Linkify

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import com.example.oncfr.Global1.pts

object Global1 {
    var pts: Int = 0
    var ptstour: Int = 0
    var health: Int = 1000
    var limD = 0f
    var nbturn: Int = 0
    var sort: Int = 0

}




class MainActivity : AppCompatActivity() {


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        val text2: TextView = findViewById(R.id.result)
        val rst: ImageView = findViewById(R.id.rstscore)

        val rules = findViewById<Button>(R.id.rules)
        rules.setOnClickListener {
            startActivity(Intent(applicationContext, RulesActivity::class.java))
        }

        val start = findViewById<Button>(R.id.start)
        start.setOnClickListener {
            Global1.nbturn = 0
            Global1.pts = 0
            Global1.ptstour = 0


            startActivity(Intent(applicationContext, GameActivity::class.java))
            finish()

        }
        val textfin = getString(R.string.score, Global1.pts)

        text2.text = textfin

        val textJa = findViewById<TextView>(R.id.urlja)
        Linkify.addLinks(textJa, Linkify.WEB_URLS)
        var max: Int = 0

        val scoremax = findViewById<TextView>(R.id.scoremax)
        val sharedPreferences = getSharedPreferences("memory", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val record = sharedPreferences.getString("record", "${Global1.pts}")
        if (record != null) {
            max = record.toInt()

            if (Global1.pts > max) {
                max = Global1.pts

            }
        }
        fun affscore() {
        editor.putString("record", "$max")
        editor.apply()
        val textRecord = getString(R.string.record1, max)
        scoremax.text = textRecord
    }

    affscore()

        fun resetscore(){
            max = 0
            affscore()
        }
    rst.setOnClickListener {
        resetscore()
    }



       /*  fun onResume() {
            super.onResume()
            // Mettez à jour votre affichage ici

            val textfin = getString(R.string.score, Global1.pts)

            text2.text = textfin // $Global1.pts"
        }*/



    }
}




