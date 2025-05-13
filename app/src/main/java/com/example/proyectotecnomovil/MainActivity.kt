package com.example.proyectotecnomovil

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.proyectotecnomovil.model.Productor
import com.example.proyectotecnomovil.model.Producto


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

/*Activity para todas las funcionalidades de la app,
* exceptuando Login, Registro y Splash*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Datos de prueba
            val productores = listOf(
                Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
                Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
                Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico),
                Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
                Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
                Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico),Productor("Laura Tejidos", "Textiles", R.drawable.textiles),
                Productor("Eco Jabones", "Cosmética natural", R.drawable.jabones),
                Productor("Campo Vivo", "Alimentos orgánicos", R.drawable.alimento_organico)
            )

            val productosFavoritos = listOf(
                Producto("Jabón de Lavanda"),
                Producto("Almohadón de Llama"),
                Producto("Miel Pura"),
                Producto ("Jabón de Lavanda"),
                Producto("Almohadón de Llama"),
                Producto("Miel Pura"),
                Producto ("Jabón de Lavanda"),
                Producto("Almohadón de Llama"),
                Producto("Miel Pura")
            )

            MainScreen(
                productores = productores,
                favoritos = productosFavoritos,
                onProductorClick = { /* Acción al tocar productor */ },
                onProductoClick = { /* Acción al tocar producto */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    productores: List<Productor>,
    favoritos: List<Producto>,
    onProductorClick: (Productor) -> Unit,
    onProductoClick: (Producto) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Manos Locales") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = "Emprendedores",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(productores) { productor ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onProductorClick(productor) },
                        elevation = CardDefaults.cardElevation(),
                    ) {
                        Box(modifier = Modifier.height(150.dp)) {
                            AsyncImage(
                                model = productor.imagenUrl,
                                contentDescription = "Imagen del productor",
                                modifier = Modifier

                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop

                            )
                            // Capa oscura para mejorar la legibilidad del texto
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.4f))
                            )

                            Row(modifier = Modifier.padding(16.dp)) {
                                //Icon(Icons.Default.Home, contentDescription = null)
                                //Spacer(Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = productor.nombre,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.White
                                    )
                                    Text(
                                        text = productor.categoria,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            /*Esto tendria que ser una funcion creo que la llamemos cuando accedamos a
             favoritos desde el home*/
            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                items(favoritos) { producto ->
                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(end = 8.dp)
                            .clickable { onProductoClick(producto) }
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                            Text(producto.nombre, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
