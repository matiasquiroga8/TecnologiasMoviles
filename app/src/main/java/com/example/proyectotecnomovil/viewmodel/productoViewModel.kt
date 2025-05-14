package com.example.proyectotecnomovil.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.proyectotecnomovil.model.Producto

class ProductoViewModel : ViewModel() {

    var productosFavoritos = mutableStateListOf<Producto>()
        private set

    fun agregarAFavoritos(producto: Producto) {
        if (producto !in productosFavoritos) {
            productosFavoritos.add(producto)
        }
    }

    fun quitarDeFavoritos(producto: Producto) {
        productosFavoritos.remove(producto)
    }
}
