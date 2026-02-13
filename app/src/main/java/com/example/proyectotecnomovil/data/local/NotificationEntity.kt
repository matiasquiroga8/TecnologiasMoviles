package com.example.proyectotecnomovil.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notificaciones")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val mensaje: String,
    val fecha: Long, // Guardamos la hora en milisegundos
    val leida: Boolean = false
)