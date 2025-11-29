package com.gabri.sudoku.views

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.gabri.sudoku.R
import androidx.appcompat.app.AppCompatActivity
import com.gabri.sudoku.objects.JuegoObj
import com.gabri.sudoku.utils.DialogUtils
import kotlin.system.exitProcess

class ModeActivity: AppCompatActivity() {
    private val dUtils: DialogUtils = DialogUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mode_activity)

        val facilBtn: Button = findViewById<Button>(R.id.facilBtn)
        facilBtn.tag = "facil"
        setMode(facilBtn)

        val medioBtn: Button = findViewById<Button>(R.id.medioBtn)
        medioBtn.tag = "medio"
        setMode(medioBtn)

        val dificilBtn: Button = findViewById<Button>(R.id.dificilBtn)
        dificilBtn.tag = "dificil"
        setMode(dificilBtn)

        val salirBtn: Button = findViewById<Button>(R.id.salirBtn)
        salirBtn.setOnClickListener { e ->
            var str = "¿ Estas seguro que quieres salir ?"
            this.dUtils.insertExitDialog(this, str)

        }
    }

    private fun setMode(btn: Button){
        btn.setOnClickListener { e ->
            val intent = Intent(this@ModeActivity, NombreActivity::class.java)
            // val dificutad = btn.tag.toString();
            // intent.putExtra("modo", dificutad)
            JuegoObj.mode = btn.tag.toString()
            startActivity(intent)
        }
    }
}

