package com.example.proyectotecnomovil.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductorFavoritoDao {
    // 1. Guardar: Esto ya funciona perfecto con tu nueva Entity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorito: ProductorFavoritoEntity)

    // 2. Borrar (Opción A): Borrar pasando el objeto entero (lo que tienes ahora)
    @Delete
    suspend fun delete(favorito: ProductorFavoritoEntity)

    // 3. Borrar (Opción B - RECOMENDADA): Borrar solo con el ID
    // Es mucho más práctico porque a veces en la UI solo tienes el ID a mano.
    @Query("DELETE FROM productores_favoritos WHERE id = :id")
    suspend fun deleteById(id: String)

    // 4. Leer TODO: Esta es la clave.
    // Devuelve la lista completa con nombre, imagen y UBICACIÓN.
    // Ideal para mostrar la pantalla de "Mis Favoritos" offline.
    @Query("SELECT * FROM productores_favoritos")
    suspend fun getAllFavorites(): List<ProductorFavoritoEntity>

    // 5. Leer solo IDs: Útil para saber rápido si el corazón va rojo o gris
    @Query("SELECT id FROM productores_favoritos")
    suspend fun getAllIds(): List<String>
}