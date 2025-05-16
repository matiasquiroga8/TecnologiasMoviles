package com.example.proyectotecnomovil.model


data class Productor(
    val nombre: String,
    val categoria: String,
    val productos: List<Producto>, // <- muchos productos
    val imagenUrl: String? = null,
    val imagenRes: Int? = null
)
