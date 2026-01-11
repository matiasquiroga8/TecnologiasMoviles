package com.example.proyectotecnomovil.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// Objeto Singleton: configura y crea la instancia de Retrofit.
object ApiClient {
    private const val BASE_URL = "https://695bd5271d8041d5eeb8a48a.mockapi.io/api/v1/"

    val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}