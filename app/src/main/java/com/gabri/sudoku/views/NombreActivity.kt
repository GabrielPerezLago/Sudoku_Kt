package com.gabri.sudoku.views

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gabri.sudoku.R
import com.gabri.sudoku.objects.JuegoObj
import com.gabri.sudoku.utils.DialogUtils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.graphics.Color

class NombreActivity: AppCompatActivity(){
    private val dUtils: DialogUtils = DialogUtils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_nombre_activity)


        val input: TextInputEditText = findViewById<TextInputEditText>(R.id.inputNombre)
        val inpLayout: TextInputLayout = findViewById<TextInputLayout>(R.id.inputLayout)
        val iniciar: Button = findViewById<Button>(R.id.iniciarBtn)

        inpLayout.apply {
            boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
            setBoxStrokeColor(Color.parseColor("#10FE18")) // borde verde
            setBoxBackgroundColor(Color.TRANSPARENT) // fondo transparente
            setBoxCornerRadii(12f, 12f, 12f, 12f)
        }

        input.apply {
            setTextColor(Color.parseColor("#10FE18"))
            setHintTextColor(Color.parseColor("#10FE18"))
            textAlignment = TextInputEditText.TEXT_ALIGNMENT_CENTER
            textSize = 18f
            setBackgroundColor(Color.TRANSPARENT) // quitar fondo predeterminado
            isCursorVisible = true
        }
        iniciar.setOnClickListener { e ->
            if (input.text.toString() != ""){
               val intent = Intent(this@NombreActivity, SudokuActivity::class.java)
                JuegoObj.nombre = input.text.toString()
                startActivity(intent)
            } else {
                dUtils.insertErrDialog(this, "Debes Insertar un nombre para empezar")
            }
        }
    }

}