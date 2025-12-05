package com.gabri.sudoku.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabri.sudoku.R
import com.gabri.sudoku.adapters.JuegoAdapter
import com.gabri.sudoku.controller.JuegoPuntuacionesController
import com.gabri.sudoku.utils.DialogUtils
import com.gabri.sudoku.utils.connectUtils.ApiClient
import kotlinx.coroutines.launch

class PuntuacionesActivity: AppCompatActivity() {
    private val dUtils = DialogUtils()
    private lateinit var rPuntuaciones: RecyclerView
    private var adapter: JuegoAdapter = JuegoAdapter(emptyList());
    private val jController: JuegoPuntuacionesController = JuegoPuntuacionesController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.puntuaciones_activity)

        rPuntuaciones = findViewById<RecyclerView>(R.id.puntuacionRecycler)
        rPuntuaciones.layoutManager = LinearLayoutManager(this)

        val facil: Button = findViewById<Button>(R.id.facilRankBtn)
        val medio: Button = findViewById<Button>(R.id.medioRankBtn)
        val dificil: Button = findViewById<Button>(R.id.dificilRankBtn)
        val exitBtn: Button = findViewById<Button>(R.id.exitRankingBtn)

        showLoadRanck()

        renderBtnListener(facil, "facil")
        renderBtnListener(medio, "medio")
        renderBtnListener(dificil, "dificil")
        renderExitBtn(exitBtn)

    }

    private fun showLoadRanck() {
        lifecycleScope.launch {
            try {
                val puntuaciones = jController.getPuntuaciones("facil")
                adapter.updatePuntuaciones(puntuaciones)
                rPuntuaciones.adapter = adapter
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun renderBtnListener(btn: Button, dif: String) {
        btn.setOnClickListener { listener ->
            lifecycleScope.launch {
                try {
                    val punt = jController.getPuntuaciones(dif)
                    adapter.updatePuntuaciones(punt)
                    rPuntuaciones.adapter = adapter
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    private fun renderExitBtn(btn: Button) {
        btn.setOnClickListener { l ->
            dUtils.insertBackInicioDialog(this@PuntuacionesActivity, "Alerta", "¿Estas seguro de que quieres salir?")
        }
    }

}