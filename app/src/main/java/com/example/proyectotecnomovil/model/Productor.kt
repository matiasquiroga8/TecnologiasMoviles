package com.example.proyectotecnomovil.model

import com.google.gson.annotations.SerializedName


data class Productor(
    val id: String,
    val nombre: String,
    val categoria: String,
    val productos: List<Producto>, // <- muchos productos
    // Ponemos String para recibir URLs de internet
    // @SerializedName asegura que si el JSON trae "imagen", lo guarde aca
    @SerializedName("imagen")
    val imagenUrl: String,
    val ubicacion: String = "Córdoba, Argentina"
)

