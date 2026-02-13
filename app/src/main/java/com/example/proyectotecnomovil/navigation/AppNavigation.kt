package com.example.proyectotecnomovil.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyectotecnomovil.model.Productor
import com.example.proyectotecnomovil.screens.*
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    productores: List<Productor>,
    viewModelProducto: ProductoViewModel,
    viewModelProductor: ProductorViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "biometric_check"
    ) {
        // 1. PANTALLA DE HUELLA
        composable("biometric_check") {
            val activity = (LocalContext.current as? Activity)
            BiometricAuthScreen(
                onAuthenticated = {
                    // Si la huella es correcta, vamos al Home
                    // y borramos la pantalla de huella del historial
                    navController.navigate(AppScreens.HomeScreen.route) {
                        popUpTo("biometric_check") { inclusive = true }
                    }
                },

                onCancel = {
                    // Si falla o cancela, volvemos al Login o cerramos
                    // Asegúrate de que LoginScreen esté en el grafo si lo usas aquí,
                    // si no, puedes usar activity.finish() pasando el contexto.
                    // Por ahora, asumimos que quieres ir al login:
//                    navController.navigate(AppScreens.LoginScreen.route) {
//                        popUpTo("biometric_check") { inclusive = true }
//                    }
                    activity?.finish()
                }
            )
        }

        // 2. HOME (Segunda pantalla, después de la huella)
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                productores = productores,
                viewModelProducto = viewModelProducto,
                viewModelProductor = viewModelProductor,
                onProductorClick = { productor ->
                    navController.navigate(AppScreens.ProductorDetailScreen.createRoute(productor.id))
                },
                onProductoClick = { producto ->
                    navController.navigate(AppScreens.ProductoDetailScreen.createRoute(producto.id))
                }
            )
        }

        // ... El resto de tus pantallas siguen igual ...

        // DETALLE PRODUCTOR
        composable(
            route = AppScreens.ProductorDetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val idProductor = backStackEntry.arguments?.getString("id")
            val productor = productores.find { it.id == idProductor }

            if (productor != null) {
                ProductorDetailScreen(
                    navController = navController,
                    productor = productor,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() },
                    onProductoClick = { idProducto ->
                        navController.navigate(AppScreens.ProductoDetailScreen.createRoute(idProducto))
                    }
                )
            }
        }

        // DETALLE PRODUCTO
        composable(
            route = AppScreens.ProductoDetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val idProducto = backStackEntry.arguments?.getString("id")
            val producto = productores.flatMap { it.productos }.find { it.id == idProducto }

            if (producto != null) {
                ProductoDetailScreen(
                    producto = producto,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // PERFIL
        composable(AppScreens.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }

        // CONFIGURACION
        composable(AppScreens.SettingsScreen.route) {
            val categorias = listOf("Todos") + productores.map { it.categoria }.distinct().sorted()
            SettingsScreen(
                categorias = categorias,
                onBack = { navController.popBackStack() }
            )
        }

        // NOTIFICACIONES
        composable(AppScreens.NotificationScreen.route) {
            NotificationScreen(
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }

        // LOGIN (Necesario si onCancel navega aquí)
        composable(AppScreens.LoginScreen.route) {
            // Aquí deberías llamar a tu LoginScreen si el usuario cancela la huella
            // O bien redirigirlo a AuthActivity
        }
    }
}