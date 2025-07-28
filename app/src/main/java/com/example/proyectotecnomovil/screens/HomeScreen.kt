package com.example.proyectotecnomovil.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectotecnomovil.model.Producto
import com.example.proyectotecnomovil.model.Productor
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.proyectotecnomovil.navigation.AppScreens
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import com.example.proyectotecnomovil.R
import androidx.compose.ui.platform.LocalFocusManager
import androidx.wear.compose.navigation.currentBackStackEntryAsState

@Composable
fun HomeScreen(
    navController: NavController,
    productores: List<Productor>,
    viewModelProducto: ProductoViewModel,
    onProductorClick: (Productor) -> Unit,
    onProductoClick: (Producto) -> Unit,
    viewModelProductor: ProductorViewModel
) {
    var selectedItem by remember { mutableStateOf(0) }
    var showFavourites by remember { mutableStateOf(false) }
    val productoresFavoritos = viewModelProductor.productoresFavoritos
    val productosFavoritos = viewModelProducto.productosFavoritos
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        topBar = { TopBar(navController, productores, onSettingsClick = {}) },
        bottomBar = {
            BottomNavApp(
                selectedItem = selectedItem,
                onItemSelected = {
                    selectedItem = it
                    showFavourites = (it == 1) // Solo mostrar sheet si selecciona Favoritos
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

            // 🔹 Mostrar contenido de la pantalla principal
            BodyHome(
                productores = productores,
                onProductorClick = onProductorClick,
                onToggleFavorito = { viewModelProductor.toggleFavorito(it) },
                isFavorito = { viewModelProductor.isFavorito(it) }
            )

            // 🔹 Mostrar ModalBottomSheet encima del Home
            if (showFavourites) {
                FavouriteSheet(
                    productoresFavoritos = productoresFavoritos,
                    productosFavoritos = productosFavoritos,
                    productores = productores,
                    onDismiss = {
                        showFavourites = false
                        selectedItem = 0 // Volver a Home al cerrar el sheet
                    },
                    navController = navController
                )
            }

            when (selectedItem) {
                1 -> showFavourites = true
                2 -> {
                    if (currentDestination != AppScreens.NotificationScreen.route) {
                        navController.navigate(AppScreens.NotificationScreen.route)
                    }
                }
                3 -> {
                    if (currentDestination != AppScreens.ProfileScreen.route) {
                        navController.navigate(AppScreens.ProfileScreen.route)
                    }
                }
            }

        }
    }
}

@Composable
fun BodyHome(
    productores: List<Productor>,
    onProductorClick: (Productor) -> Unit,
    onToggleFavorito: (Productor) -> Unit,
    isFavorito: (Productor) -> Boolean
) {
    Scaffold() { padding ->
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
                                model = productor.imagenRes,
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
                                var isFavorite by remember { mutableStateOf(false) }
                                Column(modifier = Modifier.weight(1f)) {
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

                                val favorito = isFavorito(productor)
                                Icon(
                                    imageVector = if (favorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = if (favorito) Color.Red else Color.White,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .border(1.dp, Color.White, CircleShape)
                                        .clickable { onToggleFavorito(productor) }
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    productores: List<Productor>,
    onSettingsClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val suggestions = remember(query) {
        if (query.isBlank()) emptyList()
        else productores.filter {
            it.nombre.contains(query, ignoreCase = true) ||
                    it.categoria.contains(query, ignoreCase = true)
        }
    }

    val focusManager = LocalFocusManager.current

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Manos Locales",
                    modifier = Modifier
                        .height(32.dp)
                        .padding(end = 8.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // 🔄 Buscador con ExposedDropdownMenuBox
                ExposedDropdownMenuBox(
                    expanded = expanded && suggestions.isNotEmpty(),
                    onExpandedChange = { expanded = it },
                    modifier = Modifier.weight(1f)
                ) {
                    TextField(
                        value = query,
                        onValueChange = {
                            query = it
                            expanded = it.isNotBlank()
                        },
                        placeholder = { Text("Buscar productor…") },
                        singleLine = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                        },
                        modifier = Modifier
                            .menuAnchor() // ✅ necesario para posicionar correctamente el menú
                            .fillMaxWidth()
                            .height(60.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.LightGray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black,
                            errorIndicatorColor = Color.Red
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded && suggestions.isNotEmpty(),
                        onDismissRequest = { expanded = false }
                    ) {
                        suggestions.forEach { productor ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(productor.nombre, fontWeight = FontWeight.Medium)
                                        Text(
                                            productor.categoria,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.Gray
                                        )
                                    }
                                },
                                onClick = {
                                    query = productor.nombre
                                    expanded = false
                                    focusManager.clearFocus()
                                    navController.navigate("productorDetail/${Uri.encode(productor.nombre)}")
                                    // Ejemplo: navController.navigate("productorDetail/${productor.nombre}")
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                IconButton(onClick = onSettingsClick) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuraciones",
                        tint = Color.Black,
                        modifier = Modifier.clickable { navController.navigate(AppScreens.SettingsScreen.route) }

                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteSheet(
    productores: List<Productor>,
    productoresFavoritos: List<Productor>,
    productosFavoritos: List<Producto>,
    onDismiss: () -> Unit,
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LaunchedEffect(Unit) {
        sheetState.show()
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        scrimColor = Color.Black.copy(alpha = 0.2f) // Ajusta la opacidad del fondo
    ) {
        Text(
            text = "Favoritos",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        if(productoresFavoritos.isEmpty()){
            Text(
                text = "No hay productores agregados en favoritos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }else{
            Text(
                text = "Productores",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            LazyRow(modifier = Modifier.padding(8.dp)) {
                items(productoresFavoritos) { productor ->
                    Card(
                        modifier = Modifier
                            .size(170.dp)
                            .padding(end = 8.dp)
                            .clickable {navController.navigate("productorDetail/${Uri.encode(productor.nombre)}")
                            }
                    ) {
                        Column {
                            AsyncImage(
                                model = productor.imagenRes, // puede ser una URL o un nombre de recurso
                                contentDescription = productor.nombre,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                            Text(productor.nombre, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }

        if(productosFavoritos.isEmpty()){
            Text(
                text = "No hay productos agregados en favoritos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }else {
            Text(
                text = "Productos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            LazyRow(modifier = Modifier.padding(8.dp)) {
                items(productosFavoritos) { producto ->

                    Card(
                        modifier = Modifier
                            .size(170.dp)
                            .padding(end = 8.dp)
                            .clickable {
                                val productor = productores.find { it.productos.contains(producto) }
                                productor?.let {
                                    navController.navigate(
                                        AppScreens.ProductoDetailScreen.createRoute(it.nombre, producto.nombre)
                                    )
                                }
                            }
                    ) {
                        Column {
                            AsyncImage(
                                model = producto.imagen, // puede ser una URL o un nombre de recurso
                                contentDescription = producto.nombre,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
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

@Composable
fun BottomNavApp(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorites,
        BottomNavItem.Notifications,
        BottomNavItem.Profile
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}

sealed class BottomNavItem(val title: String, val icon: ImageVector) {
    data object Home : BottomNavItem("Home", Icons.Default.Home)
    data object Favorites : BottomNavItem("Favoritos", Icons.Default.Favorite)
    data object Notifications : BottomNavItem("Notif.", Icons.Default.Notifications)
    data object Profile : BottomNavItem("Perfil", Icons.Default.Person)
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ProductorDetail : Screen("productor_detail")
}


