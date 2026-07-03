package com.example.oncfr

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.media.MediaPlayer
import android.os.Bundle

import android.view.MotionEvent
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.oncfr.R.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class GameActivity : AppCompatActivity() {

    private var mRight: Boolean = false
    private var mLeft: Boolean = false
    private val timeout = 15000L
    private var turn: Boolean = false
    //private lateinit var job: Job
    private lateinit var job2: Job
    private var b: Int = 0
    private var j: Int = 0
    private var chg = false
    private lateinit var timeBar: ProgressBar
    private var provi: Long = 0
    private var  timeGo: Long = 0



    @SuppressLint("ClickableViewAccessibility", "SourceLockedOrientationActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContentView(layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.game)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val playr: ImageView = findViewById(id.right)
        val playl: ImageView = findViewById(id.left)

        val rect = findViewById<RectangleView>(id.rect)

        val constraintLayout = findViewById<ConstraintLayout>(id.game)

        val constraintSet = ConstraintSet()
        val but = findViewById<ImageView>(id.but)
        val face = findViewById<ImageView>(id.je)
        val joueur = Joueur(face)
        val texttour = findViewById<TextView>(id.texttour)
        val cleantext =""
        val text1: TextView = findViewById(id.textpts)
        timeBar = findViewById(id.timeBar)
        //timeBar.min = 0
        timeBar.max = 100
        timeBar.isIndeterminate = false


        var nbbars by Delegates.notNull<Int>()
        var song = 0
        val ptsbeep = MediaPlayer.create(this, raw.ptsbeep)
        val chutebeep = MediaPlayer.create(this, raw.chutebeep)
        val choc1 = MediaPlayer.create(this, raw.choc1)
        val endtime = MediaPlayer.create(this, raw.endtime)
        val win = MediaPlayer.create(this, raw.win)
        val lose = MediaPlayer.create(this, raw.lose)
        val nbtour = MediaPlayer.create(this, raw.nbtour)
        val applause = MediaPlayer.create(this, raw.applause)



// les mouvements detection

        playr.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mRight = true

                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    mRight = false

                    true
                }

                else -> {
                    true
                }
            }
        }

        playl.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLeft = true

                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    mLeft = false

                    true
                }

                else -> {
                    true
                }
            }
        }


// debut du jeu



suspend  fun sortie(){
   if(!turn) {

        startActivity(Intent(applicationContext, MainActivity::class.java))
    // job.cancelAndJoin() // Arrêt de la boucle
       job2.cancelAndJoin()
        finish()

   }

}



  fun razJeu() {
     nbtour.start()
     Global1.nbturn += 1
     // pts = 0
    if(Global1.nbturn < 6) {
        but.setImageResource(drawable.cmd1)
        constraintSet.clone(constraintLayout)
        constraintSet.setHorizontalBias(id.but, 0.50f)
        constraintSet.setVerticalBias(id.but, 0.25f)

        joueur.face.setImageResource(drawable.a0)
        constraintSet.setHorizontalBias(id.je, 0.5f)
        constraintSet.setVerticalBias(id.je, 0.055f)
        constraintSet.applyTo(constraintLayout)
        ptsbeep.start()
        face.viewTreeObserver.addOnGlobalLayoutListener {
            val largeurJoueur = face.width.toFloat() // La largeur en pixels
            Global1.limD = (largeurJoueur / xmax) * 10
        }
        Global1.health = 1000

        val textaff = "READY \nTurn ${Global1.nbturn}"
        texttour.text = textaff

        turn = true
        rect.effaceList()

    }else{
        turn = false
       // sortie()

    }
 }

suspend fun fintour(){
    Global1.pts += Global1.ptstour
    startActivity(Intent(applicationContext, FinTour::class.java))
    job2.cancelAndJoin()
    finish()
}

 suspend fun checkWin(view1: ImageView, view2: ImageView) {
    val rect1 = Rect()
    view1.getHitRect(rect1) // Récupère le rectangle englobant la vue 1
    val rect2 = Rect()
    view2.getHitRect(rect2) // Récupère le rectangle englobant la vue 2

    if (Rect.intersects(rect1, rect2)) {
        win.start()
        Global1.sort = 1
        fintour()
    }
}


       fun jeu() {

            job2 = CoroutineScope(Dispatchers.Main).launch {
                var textaff = getString(string.textpts, Global1.ptstour, Global1.health)
                text1.text = textaff

                sortie()
                b = setbut(but)
                delay(1800)
                but.setImageResource(drawable.imagebut)

                constraintSet.clone(constraintLayout)
                constraintSet.setHorizontalBias(id.but, 0.4f)
                constraintSet.setVerticalBias(id.but, 0.99f)
                constraintSet.applyTo(constraintLayout)
                texttour.text = cleantext
                joueur.listimg.shuffle()

                chg = true
                while (chg) {
                    j = joueur.startJoueur()
                    if (!mRight || !mLeft) {
                        delay(700)

                    }
                    if (mRight && mLeft) {
                        chg = false
                    }
                }

                if (j == b) {
                    // bonus right
                    applause.start()
                    nbbars = 3
                    Toast.makeText(this@GameActivity, "BONUS", Toast.LENGTH_SHORT).show()
                }
                else {
                    // song malus
                    lose.start()
                    nbbars = 4
                    //  Global1.sort = 2
                    //  fintour()
                }

                timeGo = System.currentTimeMillis()

                launch {
                    while (true) {
                        song = rect.checkLance(face)
                        val (imgX, imgY) = joueur.mover(mLeft, mRight, song)
                        constraintSet.clone(constraintLayout)
                        constraintSet.setHorizontalBias(id.je, imgX / 100)
                        constraintSet.setVerticalBias(id.je, imgY / 100)
                        constraintSet.applyTo(constraintLayout)
                        provi = System.currentTimeMillis()
                        provi = (provi - timeGo) / 100

                        if(provi in 1..98) {
                            runOnUiThread {
                                timeBar.progress = provi.toInt()
                            }
                        }

                        Global1.ptstour += 1 + (face.y / 100).toInt() + ((Global1.health/10) - provi).toInt()

                        when (song) {
                            1 -> {
                                chutebeep.start()
                                Global1.health -= 10
                            }
                            2 -> {
                                choc1.start()
                                Global1.health += 20
                            }
                        }
                        if (Global1.health <= 0) {
                            Global1.sort = 4
                            fintour()
                        }
                        delay(50)
                        checkWin(but, face)
                    }
                }
                launch {
                    while (true) {

                        lance(rect, joueur.face.y)
                        textaff = getString(string.textpts, Global1.ptstour, Global1.health)
                        text1.text = textaff

                        if (System.currentTimeMillis() - timeGo > timeout) {
                            endtime.start()
                            // Global1.health = 0
                            Global1.sort = 3
                            fintour()

                        }

                        delay(1600)
                    }
                }
                delay(20)


            }
        }



        razJeu()
        jeu()




    }



}
