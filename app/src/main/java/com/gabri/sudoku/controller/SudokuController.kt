package com.gabri.sudoku.controller

import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.util.Log
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import com.google.android.material.textfield.TextInputEditText

class SudokuController {

    fun validarNum(tbl: Array<IntArray>, fila: Int, columna: Int, numero: Int): Boolean {
        for(i in 0 until 9) {
            if (tbl[fila][i] == numero) return false
            if (tbl[i][columna] == numero) return false
        }

        val fCuadrante = fila - fila % 3
        val cCuadrante = columna - columna % 3

        for (f in 0 until 3) {
            for (c in 0 until 3){
                if (tbl[fCuadrante + f][cCuadrante + c] == numero) return false
            }
        }

        return true
    }

    fun genTablero(tbl: Array<IntArray>): Boolean {
        for (fila in 0 until 9) {
            for (col in 0 until 9) {
                if (tbl[fila][col] == 0) {
                   val numeros = (1..9).shuffled()

                   for(n in numeros) {
                       if (validarNum(tbl, fila, col, n)){
                           tbl[fila][col] = n
                           if (genTablero(tbl)) return true
                           tbl[fila][col] = 0
                       }
                   }
                    return false
                }
            }
        }
        return true
    }

    fun ocultarCeldas(tbl: Array<IntArray>, celdas: Int): Array<IntArray> {
        val hidden = tbl.map { it.clone() }.toTypedArray()

        var ocultas = 0
        while (ocultas < celdas) {
            val f = (0 until 9).random()
            val c = (0 until 9).random()

            if(hidden[f][c] != 0){
                hidden[f][c] = 0
                ocultas++
            }
        }

        return hidden
    }
    fun renderGridTablero(context: AppCompatActivity, tbl: Array<IntArray>): GridLayout {
        val grid = GridLayout(context)
        grid.rowCount = 9
        grid.columnCount = 9
        grid.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        for(i in 0 until tbl.size){
            for(j in 0 until tbl[i].size) {
                val txtInput = TextInputEditText(context)
                txtInput.setTextSize(18f)
                txtInput.setPadding(10,10,10,10)
                txtInput.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                txtInput.setBackgroundResource(android.R.drawable.edit_text)
                txtInput.setTextColor(android.graphics.Color.parseColor("#10FE18"))


                val border = GradientDrawable()
                border.setStroke(5, android.graphics.Color.parseColor("#007704"))
                border.cornerRadius = 8f
                txtInput.background = border

                if (tbl[i][j] != 0) {
                    txtInput.setText(tbl[i][j].toString())
                    txtInput.isEnabled = false
                } else {
                    txtInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                    txtInput.filters = arrayOf(InputFilter.LengthFilter(1))
                    txtInput.setTextColor(android.graphics.Color.parseColor("#8EB9FF"))
                    txtInput.setText("")
                    txtInput.isEnabled = true
                }

                val params = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(j, 1f)
                    rowSpec = GridLayout.spec(i, 1f)
                    setMargins(2, 2, 2, 2) // separadores entre celdas
                }

                txtInput.layoutParams = params
                grid.addView(txtInput)
            }
        }
        return grid
    }

    fun compareTableros(hidden: Array<IntArray>, tbl: Array<IntArray>): Boolean {
        for (i in 0 until tbl.size) {
            for (j in 0 until tbl[i].size) {
                if (hidden[i][j] != tbl[i][j]) {
                    return false
                }
            }
        }
        return true
    }

    fun parseGrid(grid: GridLayout): Array<IntArray> {
        val arr = Array(9) { IntArray(9) }

        for (i in 0 until 9) {
            for (j in 0 until 9) {
                val index = i * 9 + j
                val view = grid.getChildAt(index) as TextInputEditText
                val txt = view.text.toString()

                arr[i][j] = if (txt.isNotEmpty()) txt.toInt() else 0
            }
        }
        return arr
    }

    private fun catchCeldas(hidden: Array<IntArray>, tbl: Array<IntArray>): List<Pair<Int, Int>> {
        val err = mutableListOf<Pair<Int, Int>>()

        for(i in 0 until tbl.size){
            for(j in 0 until tbl[i].size){
                val vlue = hidden[i][j]

                if(vlue != 0 && vlue != tbl[i][j]){
                    err.add(Pair(i, j))
                }

            }
        }
        return err
    }

    fun marcarCeldas(grid: GridLayout, hidden: Array<IntArray>, tbl: Array<IntArray>) {
        val err = this.catchCeldas(hidden, tbl)
        for ((f, c) in err){
            val index = f * 9 + c
            val celda = grid.getChildAt(index) as TextInputEditText
            celda.setTextColor(android.graphics.Color.RED)
        }
    }
    //Con esto capturo una celda random
    private fun capturarCelda(hidden: Array<IntArray>, tbl: Array<IntArray>): Pair<Int, Int>? {
        val celdas = mutableListOf<Pair<Int, Int>>()
        for(i in 0 until tbl.size){
            for (j in 0 until tbl[i].size ) {
                val hValue = hidden[i][j]
                val tValue = tbl[i][j]

                if(hValue == 0 || hValue != tValue){
                    celdas.add(Pair(i, j))
                }
            }
        }

        if (celdas.isEmpty()){
            return null
        }

        return celdas.random()
    }

    fun insertarPista(grid: GridLayout, hidden: Array<IntArray>, tbl: Array<IntArray>): Boolean {
        val celda: Pair<Int, Int>? = this.capturarCelda(hidden, tbl)

        if (celda == null) return false

        val (fila, col) = celda
        val nCelda = fila * 9 + col

        if (nCelda >= grid.childCount) {
            Log.e("Sudoku", "Índice fuera de rango: $nCelda")
            return false
        }

        val gridCel = grid.getChildAt(nCelda) as TextInputEditText
        val valorSolucion = tbl[fila][col]

        gridCel.setText(valorSolucion)
        gridCel.isEnabled = false
        gridCel.setTextColor(android.graphics.Color.parseColor("#10FE18"))

        hidden[fila][col] = valorSolucion

        return true

    }
}