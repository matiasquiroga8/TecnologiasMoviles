package com.example.proyectotecnomovil.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.proyectotecnomovil.model.Producto

class ProductoViewModel : ViewModel() {

    private val _productosFavoritos = mutableStateListOf<Producto>()
    val productosFavoritos: List<Producto> get() = _productosFavoritos

    fun toggleFavorito(producto: Producto) {
        if (_productosFavoritos.contains(producto)) {
            _productosFavoritos.remove(producto)
        } else {
            _productosFavoritos.add(producto)
        }
    }

    fun isFavorito(producto: Producto): Boolean {
        return _productosFavoritos.contains(producto)
    }
}
