package com.example.proyectotecnomovil.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectotecnomovil.data.local.AppDatabase
import com.example.proyectotecnomovil.data.local.NotificationEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).notificacionDao()

    // Convertimos el Flow de la base de datos a un Estado que la UI pueda leer
    val historialNotificaciones: StateFlow<List<NotificationEntity>> = dao.obtenerTodas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun eliminarNotificacion(notificacion: NotificationEntity) {
        viewModelScope.launch {
            dao.eliminar(notificacion)
        }
    }
}