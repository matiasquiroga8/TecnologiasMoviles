package com.example.proyectotecnomovil.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun AbrirMaps(context: Context, direccion: String) {
    // Codificamos la dirección para que sea válida en una URL
    val uri = Uri.parse("geo:0,0?q=${Uri.encode(direccion)}")
    val intent = Intent(Intent.ACTION_VIEW, uri)

    // Intentamos abrir específicamente Google Maps
    intent.setPackage("com.google.android.apps.maps")

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        // Si falla (no tiene Google Maps), quitamos el paquete específico
        // y dejamos que el sistema elija (Navegador, Waze, etc.)
        intent.setPackage(null)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Si aun así falla, avisamos al usuario
            Toast.makeText(context, "No se encontró una aplicación de mapas", Toast.LENGTH_SHORT).show()
        }
    }
}