package com.example.proyectotecnomovil.data.network
import com.example.proyectotecnomovil.model.Productor

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz con los endpoints (@GET)
interface ApiService {
    @GET("productores")
    suspend fun getProductores(): Response<List<Productor>>

    @GET("productores")
    suspend fun buscarProductores(@Query("search") query: String): Response<List<Productor>>
    // MockAPI soporta filtrado básico usando ?search=texto
}