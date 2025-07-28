package com.example.proyectotecnomovil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyectotecnomovil.model.Productor
import com.example.proyectotecnomovil.screens.HomeScreen
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel
import android.net.Uri
import com.example.proyectotecnomovil.screens.NotificationScreen
import com.example.proyectotecnomovil.screens.ProductoDetailScreen
import com.example.proyectotecnomovil.screens.ProfileScreen
import com.example.proyectotecnomovil.screens.SettingsScreen
import com.example.proyectotecnomovil.screens.ProductorDetailScreens
import com.example.proyectotecnomovil.viewmodel.NotificationViewModel


@Composable
fun AppNavigation(
    navController: NavHostController,
    productores: List<Productor>,
    viewModelProducto: ProductoViewModel,
    viewModelProductor: ProductorViewModel,
    notificationViewModel: NotificationViewModel
) {
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {

        composable(AppScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                productores = productores,
                viewModelProducto = viewModelProducto,
                viewModelProductor = viewModelProductor,
                onProductorClick = { productor ->
                    navController.navigate("productorDetail/${Uri.encode(productor.nombre)}")
                },
                onProductoClick = {}
            )
        }

        composable(AppScreens.NotificationScreen.route) {
            NotificationScreen(
                notificationViewModel = notificationViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "productorDetail/{nombre}",
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreProductor = backStackEntry.arguments
                ?.getString("nombre")
                ?.let { Uri.decode(it) }

            val productor = productores.find { it.nombre == nombreProductor }

            productor?.let {
                ProductorDetailScreens( // ⬅️ no AppScreens
                    navController = navController,
                    productor = it,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() }
                )
            }
        }


        composable(
            AppScreens.ProductorDetailScreen.route,
            listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre")
            val productor = productores.find { it.nombre == nombre }

            if (productor != null) {
                ProductorDetailScreens(
                    navController = navController,
                    productor = productor,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(AppScreens.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppScreens.SettingsScreen.route) {
            SettingsScreen(
                categorias = listOf("Todos", "Alimentos", "Artesanías"),
                onBack = { navController.popBackStack() },
                notificationViewModel = notificationViewModel
            )
        }

        composable(
            route = AppScreens.ProductoDetailScreen.route,
            arguments = listOf(
                navArgument("productor") { type = NavType.StringType },
                navArgument("producto") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val productorNombre =
                backStackEntry.arguments?.getString("productor")?.let { Uri.decode(it) }
            val productoNombre =
                backStackEntry.arguments?.getString("producto")?.let { Uri.decode(it) }

            val productor = productores.find { it.nombre == productorNombre }
            val producto = productor?.productos?.find { it.nombre == productoNombre }

            if (producto != null) {
                ProductoDetailScreen(
                    producto = producto,
                    viewModelProducto = viewModelProducto,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

