package com.example.proyectotecnomovil.screens

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectotecnomovil.MainActivity
import com.example.proyectotecnomovil.R
import com.example.proyectotecnomovil.navigation.AppScreens
import com.example.proyectotecnomovil.services.AuthService
import com.example.proyectotecnomovil.ui.theme.BorderLabelFocused
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold { innerPadding ->
        BodyLogin(Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun BodyLogin(modifier: Modifier = Modifier, navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var showResetDialog by remember { mutableStateOf(false) }
    var resetEmail by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        ) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.fondo),
                contentDescription = "Fondo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Login", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            //Text("Sign in to continue.", color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("User") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedBorderColor = BorderLabelFocused,
                    unfocusedBorderColor = Color.Gray
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedBorderColor = BorderLabelFocused,
                    unfocusedBorderColor = Color.Gray
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isBlank() || password.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Email y contraseña requeridos")
                        }
                    } else {
                        AuthService.login(
                            email = name,
                            password = password,
                            onSuccess = {
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                                (context as? Activity)?.finish()
                            },
                            onFailure = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Error: $it")
                                }
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Text("Log in")
            }

//        Button(
//            onClick = { val intent = Intent(context, MainActivity::class.java)
//                context.startActivity(intent)
//                // Esto cierra la actividad actual
//                (context as? Activity)?.finish()},
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(
//                Color.DarkGray
//            )
//        ) {
//            Text("Log in")
//        }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Forgot Password?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { showResetDialog = true }
                )

                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()

                Text(
                    "Signup!",
                    color = if (isPressed) Color.Gray else Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        navController.navigate(AppScreens.RegisterScreen.route)
                    }
                )
            }

            if (showResetDialog) {
                AlertDialog(
                    onDismissRequest = { showResetDialog = false },
                    title = { Text("Recuperar contraseña") },
                    text = {
                        Column {
                            Text("Ingresá tu email para recibir un enlace de recuperación.")
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = resetEmail,
                                onValueChange = { resetEmail = it },
                                label = { Text("Email") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            keyboardController?.hide()
                            if (resetEmail.isBlank()) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Email requerido")
                                }
                            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(resetEmail).matches()) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Formato de email inválido")
                                }
                            } else {
                                AuthService.sendPasswordResetEmail(
                                    email = resetEmail,
                                    onSuccess = {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Correo enviado con éxito")
                                        }
                                        showResetDialog = false
                                    },
                                    onFailure = {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Error: $it")
                                        }
                                    }
                                )
                            }
                        }) {
                            Text("Enviar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showResetDialog = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }

        }
    }
}

