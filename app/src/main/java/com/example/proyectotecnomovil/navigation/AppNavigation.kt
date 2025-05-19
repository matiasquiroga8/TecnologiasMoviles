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
import com.example.proyectotecnomovil.screens.RegisterScreen
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel
import android.net.Uri
import com.example.proyectotecnomovil.screens.ProfileScreen
import com.example.proyectotecnomovil.screens.SettingsScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    productores: List<Productor>,
    viewModelProducto: ProductoViewModel,
    viewModelProductor: ProductorViewModel
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
        composable(
            route = "productorDetail/{nombre}",
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreProductor = backStackEntry.arguments
                ?.getString("nombre")
                ?.let { Uri.decode(it) }

            val productor = productores.find { it.nombre == nombreProductor }

            productor?.let {
                ProductorDetailScreen(
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
                ProductorDetailScreen(
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
            val categorias = listOf("Todos") + productores.map { it.categoria }.distinct().sorted()
            //val categoriasConTodos = listOf("Todos") + categorias

            SettingsScreen(categorias, onBack = { navController.popBackStack() })
        }
    }
}

