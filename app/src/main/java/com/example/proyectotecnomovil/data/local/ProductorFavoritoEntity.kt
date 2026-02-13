package com.example.proyectotecnomovil.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productores_favoritos")
data class ProductorFavoritoEntity(
    @PrimaryKey val id: String, // Guardamos el ID del productor
    val nombre: String,
    val imagen: String,
    val ubicacion: String
)
