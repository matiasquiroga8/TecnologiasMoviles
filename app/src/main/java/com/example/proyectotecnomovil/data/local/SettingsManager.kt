package com.example.proyectotecnomovil.data.local

import android.content.Context

class SettingsManager(context: Context) {
    private val prefs = context.getSharedPreferences("ajustes_app", Context.MODE_PRIVATE)

    fun guardarCategoria(cat: String) {
        prefs.edit().putString("categoria", cat).apply()
    }

    fun leerCategoria(): String {
        return prefs.getString("categoria", "") ?: ""
    }

    fun guardarIntervalo(intervalo: String) {
        prefs.edit().putString("intervalo", intervalo).apply()
    }

    fun leerIntervalo(): String {
        return prefs.getString("intervalo", "Nunca") ?: "Nunca"
    }
}