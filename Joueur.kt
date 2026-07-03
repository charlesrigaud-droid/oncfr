package com.example.oncfr


import android.widget.ImageView


class Joueur( var face: ImageView) {
    //var health: Int = 100
    var imgX = 50f
    var imgY = 2.50f
    var listimg = mutableListOf(1,2,3,4,5,6,7,8,9,0)
    // private var song = 0

    //private var chg = false
    private var j: Int = 1

    init {
        face.setImageResource(R.drawable.a0)
    }





    fun mover(mLeft: Boolean, mRight: Boolean, song: Int) : Pair<Float, Float> {

        if( !mLeft && !mRight) {
            if (imgY > 2.9f) {
                imgY -= 1.5f
            }
        }
        else if (mLeft && mRight && song != 1) {
            // song = rect.checkLance(face)
            if(imgY < 96.80f) {
                imgY += 1.3f
            }
        }
        else if (mLeft ) {
             if (imgX > 3f) {
                imgX -= 3f
             }
        }
        else  {
            if (imgX  < 98.99f - Global1.limD ) {
                imgX += 3f
            }
        }
        return Pair(imgX, imgY)


    }

     fun startJoueur(): Int {
                j += 1
                 if(j > 9){
                     j=0
                 }
                face.setImageResource(imageList[listimg[j]])
                return listimg[j]+1
    }




}
