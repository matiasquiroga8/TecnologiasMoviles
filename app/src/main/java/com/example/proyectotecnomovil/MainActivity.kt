package com.example.proyectotecnomovil


import com.example.proyectotecnomovil.model.Productor
import com.example.proyectotecnomovil.model.Producto
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import com.example.proyectotecnomovil.screens.HomeScreen
import androidx.navigation.compose.rememberNavController


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
        val productoresFavoritos = listOf(
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

        val productosFavoritos = listOf(
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
        setContent {

            val navController = rememberNavController()
            HomeScreen(
                navController = navController,
                productores = productores,
                favoritos = productosFavoritos,
                onProductorClick = { /* Acción al tocar productor */ },
                onProductoClick = { /* Acción al tocar producto */ },
                productoresFavoritos = productoresFavoritos
            )

        }
    }
}


