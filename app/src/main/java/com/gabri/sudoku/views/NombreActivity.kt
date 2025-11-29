package com.gabri.sudoku.views

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gabri.sudoku.R
import com.gabri.sudoku.objects.JuegoObj
import com.gabri.sudoku.utils.DialogUtils
import com.google.android.material.textfield.TextInputEditText

class NombreActivity: AppCompatActivity(){
    private val dUtils: DialogUtils = DialogUtils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_nombre_activity)

        val input: TextInputEditText = findViewById<TextInputEditText>(R.id.inputNombre)
        val iniciar: Button = findViewById<Button>(R.id.iniciarBtn)

        iniciar.setOnClickListener { e ->
            if (input.text.toString() != ""){
               // val intent = Intent(this@NombreActivity, SudokuActivity::class.java)
                JuegoObj.nombre = input.text.toString()
                    var nom = JuegoObj.nombre
                dUtils.insertExitDialog(this, "Esto va "  + nom)
                //startActivity(intent)
            } else {
                dUtils.insertErrDialog(this, "Debes Insertar un nombre para empezar")
            }
        }
    }

}