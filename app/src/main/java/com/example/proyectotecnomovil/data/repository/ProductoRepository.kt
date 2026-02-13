package com.example.proyectotecnomovil.data.repository

import android.content.Context
import com.example.proyectotecnomovil.data.FakeData
import com.example.proyectotecnomovil.data.local.AppDatabase
import com.example.proyectotecnomovil.data.local.ProductoFavoritoEntity
import com.example.proyectotecnomovil.data.local.ProductorFavoritoEntity
import com.example.proyectotecnomovil.data.network.ApiClient
import com.example.proyectotecnomovil.model.Productor

// data/repository/ProductoRepository.kt
class ProductoRepository(private val context: Context) {

    private val db = AppDatabase.getDatabase(context)
    private val productorFavoritoDao = db.productorDao()
    private val productoFavoritoDao = db.productoDao()

    // Función que decide de dónde sacar los datos
    suspend fun obtenerProductores(): List<Productor> {
        return try {
            // Intenta traer de la API
            val respuesta = ApiClient.service.getProductores()
            if (respuesta.isSuccessful) {
                val listaApi = respuesta.body() ?: emptyList()

                // --- FILTRO DE SEGURIDAD (NUEVO) ---
                // Recorremos la lista y eliminamos los productos que llegaron con ID nulo
                // (esto arregla el crash aunque la API mande datos sucios)
                listaApi.map { productor ->
                    productor.copy(
                        productos = productor.productos.filter { it.id != null }
                    )
                }
            } else {
                //Si falla la API usamos FakeData
                FakeData.productores
                //emptyList()
            }
        } catch (e: Exception) {
            //Si no hay internet, usamos FakeData
            FakeData.productores
            //emptyList()
        }
    }

    suspend fun buscarProductores(query: String): List<Productor> {
        return try {
            val respuesta = ApiClient.service.buscarProductores(query)
            if (respuesta.isSuccessful) {
                respuesta.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            // Si falla la búsqueda online, filtramos la lista localmente
            FakeData.productores.filter {
                it.nombre.contains(query, true) || it.categoria.contains(query, true)
            }
           // emptyList()
        }
    }

    // --- BASE DE DATOS ---
    //Productores
    suspend fun agregarProductorFavorito(entidad: ProductorFavoritoEntity) {
        productorFavoritoDao.insert(entidad)
    }

    suspend fun eliminarProductorFavorito(id: String) {
        productorFavoritoDao.deleteById(id)
    }

    suspend fun obtenerIdsProductoresFavoritos(): List<String> {
        return productorFavoritoDao.getAllIds()
    }
    //Productos
    suspend fun agregarProductoFavorito(id: String) {
        productoFavoritoDao.insert(ProductoFavoritoEntity(id))
    }

    suspend fun eliminarProductoFavorito(id: String) {
        productoFavoritoDao.delete(ProductoFavoritoEntity(id))
    }

    suspend fun obtenerIdsProductosFavoritos(): List<String> {
        return productoFavoritoDao.getAllIds()
    }

}