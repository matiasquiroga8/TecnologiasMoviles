package com.example.proyectotecnomovil


import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectotecnomovil.screens.HomeScreen
import androidx.navigation.compose.rememberNavController
//import com.example.proyectotecnomovil.data.FakeData.productores
import com.example.proyectotecnomovil.navigation.AppNavigation
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel


/*Activity para todas las funcionalidades de la app,
* exceptuando Login, Registro y Splash*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val productoViewModel: ProductoViewModel = viewModel()
            val productorViewModel: ProductorViewModel = viewModel()
            //val notificationViewModel: NotificationViewModel = viewModel()
            val navController = rememberNavController()

            // CAMBIO CLAVE: Obtenemos la lista desde el ViewModel, no de FakeData
            val listaProductoresReal = productorViewModel.listaProductores
            /*
            HomeScreen(
                navController = navController,
                productores = listaProductoresReal,
                viewModelProducto = productoViewModel,
                onProductorClick = { productor ->
                    navController.navigate("productorDetail/${Uri.encode(productor.nombre)}")
                },
                onProductoClick = { /* Acción al tocar producto */ },
                viewModelProductor = productorViewModel
            )*/

            AppNavigation(
                navController = navController,
                productores = listaProductoresReal,
                viewModelProducto = productoViewModel,
                viewModelProductor = productorViewModel
            )

        }
    }
}


