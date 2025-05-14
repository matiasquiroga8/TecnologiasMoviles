package com.example.proyectotecnomovil


import com.example.proyectotecnomovil.model.Productor
import com.example.proyectotecnomovil.model.Producto
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectotecnomovil.screens.HomeScreen
import androidx.navigation.compose.rememberNavController
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel


/*Activity para todas las funcionalidades de la app,
* exceptuando Login, Registro y Splash*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Datos de prueba
        val productores = listOf(
            Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
            Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
            Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico),
            Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
            Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
            Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico),
            Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
            Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
            Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico)
        )
/*
        val productoresFavoritos = remember{ mutableStateListOf(
            Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
            Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
            Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico),
            Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
            Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
            Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico),
            Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
            Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
            Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico)
        ) }

        val productosFavoritos = remember{
            mutableStateListOf(
                Producto("Jabón de Lavanda"),
                Producto("Almohadón de Llama"),
                Producto("Miel Pura"),
                Producto("Jabón de Lavanda"),
                Producto("Almohadón de Llama"),
                Producto("Miel Pura"),
                Producto("Jabón de Lavanda"),
                Producto("Almohadón de Llama"),
                Producto("Miel Pura")
            )
        }*/


        setContent {
            val productoViewModel: ProductoViewModel = viewModel()
            val productosFavoritos = productoViewModel.productosFavoritos
            val productorViewModel: ProductorViewModel = viewModel()



            val navController = rememberNavController()
            HomeScreen(
                navController = navController,
                productores = productores,
                productosFavoritos = productosFavoritos,
                onProductorClick = { /* Acción al tocar productor */ },
                onProductoClick = { /* Acción al tocar producto */ },
                viewModelProductor = productorViewModel
            )

        }
    }
}


