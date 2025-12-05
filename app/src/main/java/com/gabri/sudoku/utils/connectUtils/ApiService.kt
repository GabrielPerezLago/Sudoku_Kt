package com.gabri.sudoku.utils.connectUtils

import com.gabri.sudoku.model.JuegoModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("puntuaciones/dificultad")
    suspend fun getPutuaciones(@Query("dificultad") dificultad: String): List<JuegoModel>

    @POST("puntuaciones")
    suspend fun createPuntuaciones(@Body puntuaciones: JuegoModel): Response<JuegoModel>
}