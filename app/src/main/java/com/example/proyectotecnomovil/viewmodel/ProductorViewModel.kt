package com.example.proyectotecnomovil.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectotecnomovil.data.local.ProductorFavoritoEntity // <--- Importante
import com.example.proyectotecnomovil.data.local.SettingsManager
import com.example.proyectotecnomovil.data.repository.ProductoRepository
import com.example.proyectotecnomovil.model.Productor
import kotlinx.coroutines.launch

class ProductorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductoRepository(application)
    private val settingsManager = SettingsManager(application)

    // Estado observable de productores (API)
    var listaProductores = mutableStateListOf<Productor>()
        private set

    private var listaOriginal = listOf<Productor>()

    // Lista solo de IDs para pintar los corazones rápido
    private val idsFavoritos = mutableStateListOf<String>()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        viewModelScope.launch {
            // 1. Cargar API
            listaOriginal = repository.obtenerProductores()
            actualizarOrden()

            // 2. Cargar IDs de Favoritos (DB)
            val favoritosIdsDb = repository.obtenerIdsProductoresFavoritos()
            idsFavoritos.clear()
            idsFavoritos.addAll(favoritosIdsDb)
        }
    }

    fun actualizarOrden() {
        val categoriaPreferida = settingsManager.leerCategoria()
        val listaOrdenada = if (categoriaPreferida == "Todos" || categoriaPreferida.isEmpty()) {
            listaOriginal
        } else {
            listaOriginal.sortedByDescending { it.categoria == categoriaPreferida }
        }
        listaProductores.clear()
        listaProductores.addAll(listaOrdenada)
    }

    // --- AQUÍ ESTÁ EL CAMBIO CLAVE ---
    fun toggleFavorito(productor: Productor) {
        viewModelScope.launch {
            if (idsFavoritos.contains(productor.id)) {
                // BORRAR: Si ya existe, lo borramos (aquí solo necesitamos el ID)
                repository.eliminarProductorFavorito(productor.id)
                idsFavoritos.remove(productor.id)
            } else {
                // AGREGAR: Creamos la entidad COMPLETA con Ubicación
                val nuevaEntidad = ProductorFavoritoEntity(
                    id = productor.id,
                    nombre = productor.nombre,
                    imagen = productor.imagenUrl,
                    ubicacion = productor.ubicacion // <--- Nuevo campo guardado
                )

                // Llamamos al repo pasando la entidad entera
                repository.agregarProductorFavorito(nuevaEntidad)
                idsFavoritos.add(productor.id)
            }
        }
    }

    fun buscar(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                actualizarOrden()
            } else {
                val resultados = repository.buscarProductores(query)
                listaProductores.clear()
                listaProductores.addAll(resultados)
            }
        }
    }

    fun isFavorito(productor: Productor): Boolean {
        return idsFavoritos.contains(productor.id)
    }

    // Filtra la lista cargada actualmente
    val productoresFavoritos: List<Productor>
        get() = listaProductores.filter { idsFavoritos.contains(it.id) }
}