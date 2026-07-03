package com.example.oncfr

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.RED


import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView




class RectangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val rectangles = mutableListOf<RectData>()

    // private val paint: Paint
    private val paint = Paint().apply {
        style = Paint.Style.FILL
    }


    data class RectData(val rect: Rect, val color: Int)

    fun addRectangle(rect: Rect, color: Int) {
        rectangles.add(RectData(rect, color))
       // invalidate() // Redessine la vue
    }

    fun effaceList(){
        rectangles.clear()
    }
    fun afficheRect(){
        invalidate()
    }

    fun checkLance(joueur: ImageView): Int{
        val rect1 = Rect()
        var song = 0

        joueur.getHitRect(rect1)

        rectangles.forEach { rectData ->

            if (Rect.intersects(rect1, rectData.rect)) {

                if (rectData.color == RED) {
                   song = 1

                    Global1.health -= 10
                } else {
                    song = 2
                    Global1.health += 30
                }
            }

        }
            //
            return song

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rectangles.forEach { rectData ->
            paint.color = rectData.color

            canvas.drawRect(rectData.rect, paint)
        }
    }



}