package com.example.proyectotecnomovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectotecnomovil.navigation.AppNavigation
import com.example.proyectotecnomovil.navigation.AuthNavigation

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                AuthNavigation()
            }
        }
    }





