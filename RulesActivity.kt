package com.example.oncfr

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RulesActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContentView(R.layout.activity_rules)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rules: TextView = findViewById(R.id.rules)
        val texte = " Jeu:  Quick Observer \n Étape 1 \n repérer la figure et pousser simultanément \nles deux boutons vert pour pouvoir jouer \n Étape 2 \n Faire descendre le joueur sur la cible\n par action sur les deux boutons, temps limité, en évitant les barres rouge,\nles bleue sont bénéfique.\n \n Règles provisoire."
        rules.text = texte
        val cmds: TextView = findViewById(R.id.cmds)
        val text2 = " Les deux boutons poussés simultanément\n font descendre\n Un seul fait aller à gauche, l'autre à droite\n sinon le joueur remonte"
        cmds.text = text2
    }
}
