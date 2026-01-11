package com.example.proyectotecnomovil.navigation

sealed class AppScreens(val route: String) {

    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object HomeScreen: AppScreens("home_screen")
    object ProfileScreen: AppScreens("profile_screen")
    object SettingsScreen: AppScreens("settings_screen")
    object NotificationScreen: AppScreens("notification_screen") // Agregué este por si lo usas en el BottomBar

    // Se cambio "{nombre}" por "{id}"
    object ProductorDetailScreen : AppScreens("productorDetail/{id}") {
        fun createRoute(id: String) = "productorDetail/$id"
    }
    // Para ver el detalle de un producto
    object ProductoDetailScreen : AppScreens("productoDetail/{id}") {
        fun createRoute(id: String) = "productoDetail/$id"
    }

}