package com.example.proyectotecnomovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.proyectotecnomovil.data.local.SettingsManager
import com.example.proyectotecnomovil.navigation.AppNavigation
import com.example.proyectotecnomovil.utils.NotificationScheduler
import com.example.proyectotecnomovil.viewmodel.ProductoViewModel
import com.example.proyectotecnomovil.viewmodel.ProductorViewModel
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat


/*Activity para todas las funcionalidades de la app,
* exceptuando Login, Registro y Splash*/
class MainActivity : ComponentActivity() {

    // 1. Declarar el lanzador de permisos
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permiso concedido, las notificaciones llegarán
        } else {
            // El usuario rechazó, no llegarán al sistema (pero sí a la pantalla interna)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Pedir permiso si es Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // --- INICIALIZAR NOTIFICACIONES ---
        val settingsManager = SettingsManager(this)
        val frecuenciaGuardada = settingsManager.leerIntervalo()

        // Esto asegura que el worker esté corriendo según la última configuración guardada
        NotificationScheduler.programarNotificaciones(this, frecuenciaGuardada)

        setContent {
            val productoViewModel: ProductoViewModel = viewModel()
            val productorViewModel: ProductorViewModel = viewModel()
            val navController = rememberNavController()

            // CAMBIO CLAVE: Obtenemos la lista desde el ViewModel, no de FakeData
            val listaProductoresReal = productorViewModel.listaProductores

            AppNavigation(
                navController = navController,
                productores = listaProductoresReal,
                viewModelProducto = productoViewModel,
                viewModelProductor = productorViewModel
            )

        }
    }
}


