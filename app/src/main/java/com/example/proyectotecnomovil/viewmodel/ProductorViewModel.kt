package com.example.proyectotecnomovil.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.proyectotecnomovil.model.Productor

class ProductorViewModel : ViewModel() {

    private val _productoresFavoritos = mutableStateListOf<Productor>()
    val productoresFavoritos: List<Productor> get() = _productoresFavoritos

    fun toggleFavorito(productor: Productor) {
        if (_productoresFavoritos.contains(productor)) {
            _productoresFavoritos.remove(productor)
        } else {
            _productoresFavoritos.add(productor)
        }
    }

    fun isFavorito(productor: Productor): Boolean {
        return _productoresFavoritos.contains(productor)
    }
}
