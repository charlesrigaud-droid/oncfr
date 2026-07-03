package com.example.oncfr



import android.content.res.Resources

import android.graphics.Rect

import android.widget.ImageView
import kotlinx.coroutines.delay


import kotlin.random.Random


lateinit var rectx: Rect
var y11: Int = 0
var y12: Int = 0
var choix: Int = 0
var y1: Int = 0
var x2: Int = 0


val displayMetrics = Resources.getSystem().displayMetrics
val xmax = displayMetrics.widthPixels
val ymax = displayMetrics.heightPixels

val imageList = listOf(

    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4,
    R.drawable.a5,
    R.drawable.a6,
    R.drawable.a7,
    R.drawable.a8,
    R.drawable.a9,
    R.drawable.a10,
    )



suspend fun lance(rect: RectangleView, facey: Float){
    rect.effaceList()

    rect.afficheRect()
    val y = facey.toInt()



    if((y > 151) && ( y < ymax-240)) {
       // Log.d("info", "$y")

        for (i in 1..4) {
            y12 = Random.nextInt(y + 70, (ymax - 200))
            y11 = if( y > 200) {
                Random.nextInt(30, (y - 150))
            }else y12

                choix = Random.nextInt(0, 100)
                y1 = if (choix > 70) {
                    y11
                } else {
                        y12
                }

            x2 = Random.nextInt(20, xmax - 50) // longueur du rectangle origine  -30
            var chance = Random.nextInt(0, 2)
            // a gauche ou a droite
            rectx = if (chance == 0) {
                Rect(20, y1, 20 + x2, y1 + 30)

            } else{
                Rect(xmax - x2, y1, xmax - 20, y1 + 30)
            }

           // delay(200)

            chance = Random.nextInt(0, 100)
            if (chance > 90) {
                rect.addRectangle(rectx, android.graphics.Color.BLUE)
            } else {
                rect.addRectangle(rectx, android.graphics.Color.RED)
            }
        }
    }
   
    rect.afficheRect()
}

fun setbut(but: ImageView): Int {
    val j: Int = Random.nextInt(1, 11)
    when (j) {
        1 -> but.setImageResource(R.drawable.a1)
        2 -> but.setImageResource(R.drawable.a2)
        3 -> but.setImageResource(R.drawable.a3)
        4 -> but.setImageResource(R.drawable.a4)
        5 -> but.setImageResource(R.drawable.a5)
        6 -> but.setImageResource(R.drawable.a6)
        7 -> but.setImageResource(R.drawable.a7)
        8 -> but.setImageResource(R.drawable.a8)
        9 -> but.setImageResource(R.drawable.a9)
        10 -> but.setImageResource(R.drawable.a10)
    }

    return j
}


