package com.example.proyectotecnomovil.viewmodel

import androidx.compose.runtime.mutableStateListOf
import com.example.proyectotecnomovil.model.Producto
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectotecnomovil.data.repository.ProductoRepository

import kotlinx.coroutines.launch

class ProductoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductoRepository(application)

    private val productosFavoritos = mutableStateListOf<Producto>()
    val listaProductosFavoritos: List<Producto> get() = productosFavoritos

    // Lista auxiliar solo con los IDs para verificar rápido si es favorito
    private val idsFavoritos = mutableStateListOf<String>()

    init {
        cargarFavoritos()
    }

    private fun cargarFavoritos() {
        viewModelScope.launch {
            try {
                // 1. Obtenemos los IDs de los productos que le gustaron al usuario (desde la BD)
                val idsDb = repository.obtenerIdsProductosFavoritos()
                idsFavoritos.clear()
                idsFavoritos.addAll(idsDb)

                // 2. Para mostrar la lista completa (con imagen, nombre, precio), necesitamos
                // traer todos los productos de la API y filtrar los que coinciden con los IDs guardados.
                val productores = repository.obtenerProductores()

                // Aplanamos la lista: de "Lista de Productores" sacamos una "Lista de TODOS los Productos"
                val todosLosProductos = productores.flatMap { it.productos }

                // Filtramos solo los que están en favoritos
                val favoritosReconstruidos = todosLosProductos.filter { producto ->
                    idsFavoritos.contains(producto.id)
                }

                productosFavoritos.clear()
                productosFavoritos.addAll(favoritosReconstruidos)

            } catch (e: Exception) {
                // Manejar error si es necesario
            }
        }
    }
    fun toggleFavorito(producto: Producto) {
        viewModelScope.launch {
            if (idsFavoritos.contains(producto.id)) {
                // Eliminar de BD
                repository.eliminarProductoFavorito(producto.id)
                // Eliminar de listas locales (UI)
                idsFavoritos.remove(producto.id)
                productosFavoritos.remove(producto)
            } else {
                // Agregar a BD
                repository.agregarProductoFavorito(producto.id)
                // Agregar a listas locales (UI)
                idsFavoritos.add(producto.id)
                productosFavoritos.add(producto)
            }
        }
    }

    fun isFavorito(producto: Producto): Boolean {
        return idsFavoritos.contains(producto.id)
    }
}
