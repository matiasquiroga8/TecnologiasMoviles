package com.example.proyectotecnomovil.navigation

sealed class AppScreens(val route: String) {

    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object HomeScreen: AppScreens("home_screen")
    object ProductorDetailScreen : AppScreens("productorDetail/{nombre}") {
        fun createRoute(nombre: String) = "productorDetail/$nombre"
    }
    object ProfileScreen: AppScreens("profile_screen")
}