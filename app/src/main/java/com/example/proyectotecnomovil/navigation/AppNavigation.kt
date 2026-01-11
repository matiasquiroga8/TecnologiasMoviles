package com.example.proyectotecnomovil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyectotecnomovil.model.Productor
import com.example.proyectotecnomovil.screens.HomeScreen
import com.example.proyectotecnomovil.screens.LoginScreen
import com.example.proyectotecnomovil.screens.ProductorDetailScreen
import com.example.proyectotecnomovil.screens.ProductoDetailScreen
import com.example.proyectotecnomovil.screens.RegisterScreen
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel
import android.net.Uri
import com.example.proyectotecnomovil.screens.ProfileScreen
import com.example.proyectotecnomovil.screens.SettingsScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    productores: List<Productor>, // Esta lista viene del ViewModel (ya con datos de API)
    viewModelProducto: ProductoViewModel,
    viewModelProductor: ProductorViewModel
) {
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        //HOME
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                productores = productores,
                viewModelProducto = viewModelProducto,
                viewModelProductor = viewModelProductor,
                onProductorClick = { productor ->
                    // CAMBIO: Navegamos usando el ID del productor
                    navController.navigate(AppScreens.ProductorDetailScreen.createRoute(productor.id))
                },
                onProductoClick = {producto ->
                    // CAMBIO: Navegamos usando el ID del producto
                    navController.navigate(AppScreens.ProductoDetailScreen.createRoute(producto.id))
                }
            )
        }

        //DETALLE PRODUCTOR
        composable(
            route = AppScreens.ProductorDetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val idProductor = backStackEntry.arguments?.getString("id")
            /*val nombreProductor = backStackEntry.arguments
                ?.getString("nombre")
                ?.let { Uri.decode(it) }*/
            // Buscamos en la lista por ID
            val productor = productores.find { it.id == idProductor }
            //val productor = productores.find { it.nombre == nombreProductor }

            if (productor != null) {
                ProductorDetailScreen(
                    navController = navController,
                    productor = productor,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() },
                    // ACA CONECTAMOS EL CLICK DEL PRODUCTO HACIA LA PANTALLA NUEVA
                    onProductoClick = { idProducto ->
                        navController.navigate(AppScreens.ProductoDetailScreen.createRoute(idProducto))
                    }
                )
            }
        }
        // DETALLE PRODUCTO (Por ID) - NUEVO
        composable(
            route = AppScreens.ProductoDetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val idProducto = backStackEntry.arguments?.getString("id")

            // Buscamos el producto dentro de todos los productores
            val producto = productores.flatMap { it.productos }.find { it.id == idProducto }

            if (producto != null) {
                ProductoDetailScreen(
                    producto = producto,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() }
                )
            }
        }
        /*composable(
            AppScreens.ProductorDetailScreen.route,
            listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre")
            val productor = productores.find { it.nombre == nombre }

            if (productor != null) {
                ProductorDetailScreen(
                    navController = navController,
                    productor = productor,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() }
                )
            }
        }*/

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
            //val categoriasConTodos = listOf("Todos") + categorias

            SettingsScreen(
                categorias = categorias,
                onBack = { navController.popBackStack() },
                // notificationViewModel = ... (si lo necesitas pasar)
            )
        }

        // NOTIFICACIONES
        composable(AppScreens.NotificationScreen.route) {
            // NotificationScreen(...)
        }
    }
}

