package com.example.proyectotecnomovil.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductoFavoritoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorito: ProductoFavoritoEntity)

    @Delete
    suspend fun delete(favorito: ProductoFavoritoEntity)

    @Query("SELECT id FROM productos_favoritos")
    suspend fun getAllIds(): List<String>
}