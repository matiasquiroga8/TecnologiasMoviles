package com.example.proyectotecnomovil.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectotecnomovil.data.local.SettingsManager
import com.example.proyectotecnomovil.data.repository.ProductoRepository
import com.example.proyectotecnomovil.model.Productor
import kotlinx.coroutines.launch

class ProductorViewModel(application: Application) : AndroidViewModel(application) {

    // Instancias el repositorio (o se lo pasas, pero así es fácil para empezar)
    private val repository = ProductoRepository(application)
    private val settingsManager = SettingsManager(application) // <--- Instanciamos Settings

    // Estado observable
    var listaProductores = mutableStateListOf<Productor>()
        private set

    // Copia de respaldo de los datos originales para poder reordenar sin llamar a la API
    private var listaOriginal = listOf<Productor>()

    private val idsFavoritos = mutableStateListOf<String>()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        viewModelScope.launch {
            // 1. Cargamos productores de la API
            listaOriginal = repository.obtenerProductores()

            // 2. Aplicamos el orden inmediatamente
            actualizarOrden()
            // 2. Cargamos los favoritos guardados en la DB
            val favoritosDb = repository.obtenerIdsProductoresFavoritos()
            idsFavoritos.clear()
            idsFavoritos.addAll(favoritosDb)
        }

    }

    // Nueva función para reordenar la lista según la configuración
    fun actualizarOrden() {
        val categoriaPreferida = settingsManager.leerCategoria()

        val listaOrdenada = if (categoriaPreferida == "Todos" || categoriaPreferida.isEmpty()) {
            listaOriginal // Sin orden especial
        } else {
            // Pone al principio (true) los que coinciden con la categoría
            listaOriginal.sortedByDescending { it.categoria == categoriaPreferida }
        }

        listaProductores.clear()
        listaProductores.addAll(listaOrdenada)
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

    // Nueva función para buscar
    fun buscar(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                // Si borran la búsqueda, restauramos la lista original (con el orden preferido)
                actualizarOrden()
            } else {
                // Usamos el repositorio que ya tiene la lógica de buscar en API o FakeData
                val resultados = repository.buscarProductores(query)
                listaProductores.clear()
                listaProductores.addAll(resultados)
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

}
