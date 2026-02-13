package com.example.proyectotecnomovil.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    // Trae las notificaciones ordenadas por las más nuevas primero
    @Query("SELECT * FROM notificaciones ORDER BY fecha DESC")
    fun obtenerTodas(): Flow<List<NotificationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(notificacion: NotificationEntity)

    // Opcional: Para marcar todas como leídas
    @Query("UPDATE notificaciones SET leida = 1 WHERE leida = 0")
    suspend fun marcarTodasComoLeidas()

    @Delete
    suspend fun eliminar(notificacion: NotificationEntity)
}