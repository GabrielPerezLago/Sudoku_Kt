package com.gabri.sudoku.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gabri.sudoku.R
import com.gabri.sudoku.controller.SudokuController
import com.gabri.sudoku.objects.JuegoObj
import com.gabri.sudoku.utils.ContadorUtils
import com.gabri.sudoku.utils.DialogUtils

class SudokuActivity: AppCompatActivity() {
    private val dUtils: DialogUtils = DialogUtils()
    private val cUtils: ContadorUtils = ContadorUtils()
    private val sController: SudokuController = SudokuController()
    private val tablero = Array(9) { IntArray(9) }
    private lateinit var  hiddenTbl: Array<IntArray>

    private var celdasWithMode: Int = when(JuegoObj.mode) {
        "facil" -> 20
        "medio" -> 30
        "dificil" -> 40
        else -> 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sudoku_activity)

        val nombreLbl: TextView = findViewById<TextView>(R.id.nombreLbl)
        val tiempoLbl: TextView = findViewById<TextView>(R.id.timeLbl)
        val tblLayout: LinearLayout = findViewById<LinearLayout>(R.id.tableroLayout)
        val comprobarBtn: Button = findViewById<Button>(R.id.comprobarBtn)
        val pistaBtn: Button = findViewById<Button>(R.id.pistaBtn)
        val rendirse: Button = findViewById<Button>(R.id.retreatBtn)

        nombreLbl.text = JuegoObj.nombre
        this.rendirseListener(rendirse)
        cUtils.iniciarContador(tiempoLbl)

        /* Generación de Tablero */

        tablero.forEach { it.fill(0) }
        sController.genTablero(tablero)
        print(tablero)
        hiddenTbl = sController.ocultarCeldas(tablero, celdasWithMode)

        /* Renderizar Tablero */
        tblLayout.removeAllViews()
        var tRenderizado: GridLayout = sController.renderGridTablero(this@SudokuActivity, hiddenTbl)


        tblLayout.addView(tRenderizado)
    }

    private fun rendirseListener(btn: Button) {
        btn.setOnClickListener { e ->
            dUtils.insertBackInicioDialog(this@SudokuActivity,
                "¿ Estas seguro de que quieres salir , perderas todo el progreso ?")
        }
    }



}