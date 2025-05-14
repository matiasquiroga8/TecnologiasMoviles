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
                /*Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Text(
                            text = "Login",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                ) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }*/

                AppNavigation()
            }
        }
    }





