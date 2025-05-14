package com.example.proyectotecnomovil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectotecnomovil.screens.HomeScreen
import com.example.proyectotecnomovil.screens.LoginScreen
import com.example.proyectotecnomovil.screens.RegisterScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(
                navController,
                productores = TODO(),
                favoritos = TODO(),
                onProductorClick = TODO(),
                onProductoClick = TODO()
            )
        }
    }
}