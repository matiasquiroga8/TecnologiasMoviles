package com.example.proyectotecnomovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.proyectotecnomovil.data.FakeData.productores
import com.example.proyectotecnomovil.navigation.AppNavigation
import com.example.proyectotecnomovil.viewmodel.NotificationViewModel
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
            val notificationViewModel: NotificationViewModel = viewModel()
            val navController = rememberNavController()

            AppNavigation(
                navController = navController,
                productores = productores,
                viewModelProducto = productoViewModel,
                viewModelProductor = productorViewModel,
                notificationViewModel = notificationViewModel
            )

        }
    }
}


