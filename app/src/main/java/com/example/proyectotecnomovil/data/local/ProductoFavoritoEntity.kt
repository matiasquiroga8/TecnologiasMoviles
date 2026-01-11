package com.example.proyectotecnomovil.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos_favoritos")
data class ProductoFavoritoEntity(
    @PrimaryKey val id: String
)
