package com.example.proyectotecnomovil.utils

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.proyectotecnomovil.workers.NotificationWorker
import java.util.concurrent.TimeUnit

object NotificationScheduler {

    private const val WORK_NAME = "manos_locales_notif_work"

    fun programarNotificaciones(context: Context, frecuenciaTexto: String) {
        val workManager = WorkManager.getInstance(context)

        // 1. Definir el tiempo en horas según el texto
        val intervaloHoras: Long? = when (frecuenciaTexto) {
            "Cada hora" -> 1
            "Cada 6 horas" -> 6
            "Diariamente" -> 24
            else -> null // "Nunca" u otro valor desconocido
        }

        // 2. Si es "Nunca", cancelamos todo y salimos
        if (intervaloHoras == null) {
            workManager.cancelUniqueWork(WORK_NAME)
            return
        }

        // 3. Crear la petición de trabajo repetitivo
        // Nota: Android requiere un mínimo de 15 minutos para tareas periódicas.
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
            intervaloHoras, TimeUnit.HOURS
        ).build()

        // 4. Encolar el trabajo con la política UPDATE
        // Esto es clave: si ya existía uno, lo actualiza con el nuevo tiempo.
        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}