package com.example.proyectotecnomovil.model

data class Producto(
    val nombre: String,
    val imagen: String, // URL o nombre de recurso local
    val precio: Double,
    val descripcion: String,
    val imagenUrl: String? = null,
    val imagenRes: Int? = null
)