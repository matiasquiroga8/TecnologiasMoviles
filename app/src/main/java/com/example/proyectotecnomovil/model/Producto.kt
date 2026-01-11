package com.example.proyectotecnomovil.model

import com.google.gson.annotations.SerializedName


data class Producto(
    val id: String,
    val nombre: String,
    @SerializedName("imagen")
    val imagen: String, // URL de la imagen
    val precio: Double,
    val descripcion: String
)

