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
import com.example.proyectotecnomovil.screens.RegisterScreen
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel


@Composable
fun AppNavigation(
    navController: NavHostController,
    productores: List<Productor>,
    viewModelProducto: ProductoViewModel,
    viewModelProductor: ProductorViewModel
) {
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                productores = productores,
                viewModelProducto = viewModelProducto,
                viewModelProductor = viewModelProductor,
                onProductorClick = { productor ->
                    navController.navigate("${AppScreens.ProductorDetailScreen.route}/${productor.nombre}")
                },
                onProductoClick = {}
            )
        }
        composable(
            route = "productorDetail/{nombre}",
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreProductor = backStackEntry.arguments?.getString("nombre")

            val productor = productores.find { it.nombre == nombreProductor }

            productor?.let {
                ProductorDetalleScreen(
                    productor = it,
                    viewModelProducto = viewModelProducto
                )
            }
        }
    }
}

@Composable
fun ProductorDetalleScreen(productor: Productor, viewModelProducto: ProductoViewModel) {

}
