package com.gabri.sudoku.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gabri.sudoku.R
import com.gabri.sudoku.objects.JuegoObj
import com.gabri.sudoku.utils.ContadorUtils
import com.gabri.sudoku.utils.DialogUtils

class SudokuActivity: AppCompatActivity() {
    private val dUtils: DialogUtils = DialogUtils()
    private val cUtils: ContadorUtils = ContadorUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sudoku_activity)

        val nombreLbl: TextView = findViewById<TextView>(R.id.nombreLbl)
        val tiempoLbl: TextView = findViewById<TextView>(R.id.timeLbl)
        val comprobarBtn: Button = findViewById<Button>(R.id.comprobarBtn)
        val pistaBtn: Button = findViewById<Button>(R.id.pistaBtn)
        val rendirse: Button = findViewById<Button>(R.id.retreatBtn)

        nombreLbl.text = JuegoObj.nombre
        this.rendirseListener(rendirse)
        cUtils.iniciarContador(tiempoLbl)

    }

    private fun rendirseListener(btn: Button) {
        btn.setOnClickListener { e ->
            dUtils.insertBackInicioDialog(this,
                "¿ Estas seguro de que quieres salir , perderas todo el progreso ?")
        }
    }



}