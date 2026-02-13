package com.example.proyectotecnomovil.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.proyectotecnomovil.R
import com.example.proyectotecnomovil.data.local.AppDatabase
import com.example.proyectotecnomovil.data.local.NotificationEntity
import kotlinx.coroutines.runBlocking

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        // 1. Generar datos aleatorios
        val mensajes = listOf(
            "¡Nuevos productos orgánicos!",
            "Descuentos en textiles hoy",
            "Revisá los nuevos quesos artesanales",
            "Mermeladas caseras en oferta"
        )
        val mensajeElegido = mensajes.random()
        val titulo = "Manos Locales"

        // 2. Mostrar notificación visual (En la barra de estado del celular)
        mostrarNotificacionSistema(titulo, mensajeElegido)

        // 3. Guardar en Base de Datos (Para que aparezca en la pantalla de Notificaciones de la app)
        guardarEnHistorial(titulo, mensajeElegido)

        return Result.success()
    }

    private fun mostrarNotificacionSistema(titulo: String, mensaje: String) {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "ofertas_channel"

        // Crear el canal (Obligatorio para Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Ofertas",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        // Construir la notificación visual
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // Para que se borre al tocarla
            .build()

        manager.notify(1, notification)
    }

    private fun guardarEnHistorial(titulo: String, mensaje: String) {
        // Instanciar la base de datos
        val db = AppDatabase.getDatabase(applicationContext)

        // Crear la entidad
        val nuevaNotificacion = NotificationEntity(
            titulo = titulo,
            mensaje = mensaje,
            fecha = System.currentTimeMillis(), // Hora actual
            leida = false
        )

        // IMPORTANTE: Usamos runBlocking porque doWork es síncrono,
        // pero insertar en Room debe hacerse en un hilo secundario (IO).
        // runBlocking bloquea este hilo del Worker hasta que la DB termine de guardar.
        runBlocking {
            db.notificacionDao().insertar(nuevaNotificacion)
        }
    }
}