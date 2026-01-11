package com.example.proyectotecnomovil.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectotecnomovil.data.repository.ProductoRepository
import com.example.proyectotecnomovil.model.Productor
import kotlinx.coroutines.launch

class ProductorViewModel(application: Application) : AndroidViewModel(application) {

    // Instancias el repositorio (o se lo pasas, pero así es fácil para empezar)
    private val repository = ProductoRepository(application)

    // Estado observable
    var listaProductores = mutableStateListOf<Productor>()
        private set

    private val idsFavoritos = mutableStateListOf<String>()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        viewModelScope.launch {
            // 1. Cargamos productores de la API
            val datosApi = repository.obtenerProductores()
            listaProductores.clear()
            listaProductores.addAll(datosApi)

            // 2. Cargamos los favoritos guardados en la DB
            val favoritosDb = repository.obtenerIdsProductoresFavoritos()
            idsFavoritos.clear()
            idsFavoritos.addAll(favoritosDb)
        }

    }

    fun toggleFavorito(productor: Productor) {
        viewModelScope.launch {
            if (idsFavoritos.contains(productor.id)) {
                // Si ya es favorito, lo borramos de la DB y de la lista local
                repository.eliminarProductorFavorito(productor.id)
                idsFavoritos.remove(productor.id)
            } else {
                // Si no, lo agregamos
                repository.agregarProductorFavorito(productor.id)
                idsFavoritos.add(productor.id)
            }
        }
    }

    fun isFavorito(productor: Productor): Boolean {
        // Esta función es rápida para la UI
        return idsFavoritos.contains(productor.id)
    }

    // Getter para la pantalla de favoritos (filtra la lista principal)
    val productoresFavoritos: List<Productor>
        get() = listaProductores.filter { idsFavoritos.contains(it.id) }


//    private val _productoresFavoritos = mutableStateListOf<Productor>()
//    val productoresFavoritos: List<Productor> get() = _productoresFavoritos
//
//    fun toggleFavorito(productor: Productor) {
//        if (_productoresFavoritos.contains(productor)) {
//            _productoresFavoritos.remove(productor)
//        } else {
//            _productoresFavoritos.add(productor)
//        }
//    }
//
//    fun isFavorito(productor: Productor): Boolean {
//        return _productoresFavoritos.contains(productor)
//    }
}
