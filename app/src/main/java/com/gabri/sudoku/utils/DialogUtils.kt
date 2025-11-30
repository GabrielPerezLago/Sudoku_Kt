package com.gabri.sudoku.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gabri.sudoku.views.InicioActivity
import kotlin.system.exitProcess


class DialogUtils {

    /**
     * @param context {AppCompatActivity}
     * @param errString {String}
     *
     * Esta funcion genera un dialogo de error.
     *
     * Recive una Actividad y el mensaje que quieres que aparezca por pantalla.
     *
     * @return Dialog
     */
    fun insertErrDialog(context: AppCompatActivity, errString: String){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Errror:")
        dialog.setMessage(errString)

        dialog.setPositiveButton("Aceptar") {dialog, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    /**
     * @param context {AppCompatActivity}
     * @param str {String}
     *
     * Esta funcion crea una alerta que solicita la confirmacion al usuario
     * para salir de la aplicacion.
     *
     * Recive una actividad y tambien en mensaque que se desea que aparezca por pantalla.
     *
     * @return dialog
     */
    fun insertExitDialog(contex: AppCompatActivity, str: String){
        val dialog = AlertDialog.Builder(contex)
        dialog.setTitle("Alerta")
        dialog.setMessage(str)

        dialog.setPositiveButton("Aceptar"){ _, _ ->
            contex.finishAffinity()
            exitProcess(0)

        }

        dialog.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    fun insertBackInicioDialog(context: AppCompatActivity, str: String) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Info")
        dialog.setMessage(str)

        dialog.setPositiveButton("Aceptar"){ _, _ ->
            var intent = Intent(context, InicioActivity::class.java)
            context.startActivity(intent)

        }

        dialog.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }
}