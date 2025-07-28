package com.example.proyectotecnomovil.navigation

import android.net.Uri

sealed class AppScreens(val route: String) {

    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object HomeScreen: AppScreens("home_screen")
    object ProductorDetailScreen : AppScreens("productorDetail/{nombre}") {
        fun createRoute(nombre: String) = "productorDetail/$nombre"
    }
    object ProfileScreen: AppScreens("profile_screen")
    object SettingsScreen: AppScreens("settings_screen")
    object NotificationScreen: AppScreens("notification_screen")
    object ProductoDetailScreen : AppScreens("productoDetail/{productor}/{producto}") {
        fun createRoute(productor: String, producto: String): String {
            return "productoDetail/${Uri.encode(productor)}/${Uri.encode(producto)}"
        }
    }
}