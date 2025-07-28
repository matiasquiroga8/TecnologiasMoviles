package com.example.proyectotecnomovil.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList

class NotificationViewModel : ViewModel() {

    private val _frecuencia = mutableStateOf("Nunca")
    val frecuencia: State<String> get() = _frecuencia

    private val _notificaciones = mutableStateListOf<String>()
    val notificaciones: SnapshotStateList<String> get() = _notificaciones

    fun setFrecuencia(valor: String) {
        _frecuencia.value = "valor"
        generarNotificaciones() // Opcional: generar cuando cambia
    }

    private fun generarNotificaciones() {
        _notificaciones.clear()

        when (_frecuencia.value) {
            "Nunca" -> {} // Nada
            "Cada hora" -> _notificaciones.add("Notificación generada hace 1 hora")
            "Cada 6 horas" -> _notificaciones.add("Notificación generada hace 6 horas")
            "Diariamente" -> _notificaciones.add("Notificación de hoy")
        }
    }

    init {
        generarNotificaciones()
    }
}