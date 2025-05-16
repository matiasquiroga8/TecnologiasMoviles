package com.example.proyectotecnomovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectotecnomovil.navigation.AppNavigation

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                AppNavigation(
                    navController = TODO(),
                    productores = TODO(),
                    viewModelProducto = TODO(),
                    viewModelProductor = TODO()
                )
            }
        }
    }





