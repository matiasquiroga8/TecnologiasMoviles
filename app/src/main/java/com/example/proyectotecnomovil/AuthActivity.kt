package com.example.proyectotecnomovil

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectotecnomovil.navigation.AppNavigation
import com.example.proyectotecnomovil.navigation.AuthNavigation
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // Ya hay una sesión activa → ir directamente al MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            // No hay sesión → mostrar pantalla de login/register
            enableEdgeToEdge()
            setContent {
                AuthNavigation()
            }
        }
    }
}





