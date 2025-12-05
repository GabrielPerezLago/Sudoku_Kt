package com.gabri.sudoku.views

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gabri.sudoku.R
import com.gabri.sudoku.utils.DialogUtils

class InicioActivity : AppCompatActivity(){
    private val dUtils: DialogUtils = DialogUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio_activity)

        val inicioButton: Button = findViewById<Button>(R.id.inicioBtn)
        val exitBtn: Button = findViewById<Button>(R.id.exitBtn)
        val puntuaciones: Button = findViewById<Button>(R.id.puntuacionesBtn)

        inicioButton.setOnClickListener { e ->
            var intent = Intent(this@InicioActivity, ModeActivity::class.java);
            startActivity(intent)
        }

        exitBtn.setOnClickListener { e ->
            var str = "¿ Estas seguro de que quieres salir ?"
            this.dUtils.insertExitDialog(this, str)
        }

        puntuaciones.setOnClickListener { e ->
            val intent = Intent(this@InicioActivity, PuntuacionesActivity::class.java)
            startActivity(intent)
        }

    }
}