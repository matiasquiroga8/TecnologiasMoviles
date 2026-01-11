package com.example.proyectotecnomovil.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductorFavoritoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorito: ProductorFavoritoEntity)

    @Delete
    suspend fun delete(favorito: ProductorFavoritoEntity)

    @Query("SELECT id FROM productores_favoritos")
    suspend fun getAllIds(): List<String>
}