package com.gabri.sudoku.utils

import android.os.Handler
import android.os.Looper
import android.widget.TextView

class ContadorUtils {
    private var segundos = 0
    fun iniciarContador(tiempoLbl: TextView) {
        val looper = Handler(Looper.getMainLooper())

        looper.post( object : Runnable {
            override fun run() {
                segundos++
                tiempoLbl.text = formatTime(segundos)
                looper.postDelayed(this, 1000)
            }
        })
    }

    /**
     * @param seconds: Int
     *
     * Esta funcion se encarga de convertir el contador,
     * de segundos simples aun formato de relog digital
     */
    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", minutes, secs)
    }

    fun getTiempo(): String {
        return formatTime(segundos)
    }
}