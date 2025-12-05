package com.gabri.sudoku.controller

import com.gabri.sudoku.model.JuegoModel
import androidx.lifecycle.lifecycleScope
import com.gabri.sudoku.utils.connectUtils.ApiClient
import kotlinx.coroutines.launch
import java.util.logging.Logger

class JuegoPuntuacionesController {
    private val ApiClient = ApiClient()
    suspend fun getPuntuaciones(dif: String): List<JuegoModel>  {
            return try {
                ApiClient.apiService.getPutuaciones(dif)
            } catch (ex: Exception) {
                println(ex.message)
                emptyList()
            }
    }
}