package com.example.proyectotecnomovil.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.proyectotecnomovil.AuthActivity
import com.example.proyectotecnomovil.components.FechaNacimientoField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, onBack: () -> Unit) {
    // Estados de la UI
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }

    // Selector de imagen (Solo visual local)
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            profileImageUri = uri
        }
    }

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    // Cargar datos existentes al abrir la pantalla
    LaunchedEffect(Unit) {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            email = user.email ?: ""

            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        userName = document.getString("name") ?: ""
                        dateOfBirth = document.getString("dateOfBirth") ?: ""
                    }
                }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen de perfil (Visual)
                Image(
                    painter = rememberAsyncImagePainter(
                        model = profileImageUri ?: "https://cdn-icons-png.flaticon.com/512/149/149071.png"
                    ),
                    contentDescription = "Imagen de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                        .clickable { imagePickerLauncher.launch("image/*") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Nombre de usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                var showPassword by remember { mutableStateOf(false) }

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Nueva contraseña (opcional)") },
                    placeholder = { Text("Dejar vacío para mantener actual") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                FechaNacimientoField(
                    dateOfBirth = dateOfBirth,
                    onDateSelected = { dateOfBirth = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón Guardar Cambios
                Button(
                    onClick = {
                        val user = auth.currentUser
                        if (user != null) {
                            isLoading = true
                            guardarDatosSinImagen(
                                user, db, context as Activity, userName, email, password, dateOfBirth
                            ) { isLoading = false }
                        } else {
                            Toast.makeText(context, "Sesión no válida", Toast.LENGTH_SHORT).show()
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isLoading) "Guardando..." else "Guardar cambios")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        auth.signOut()
                        val intent = Intent(context, AuthActivity::class.java)
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar sesión")
                }
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

// Función maestra para guardar datos (Texto)
private fun guardarDatosSinImagen(
    user: FirebaseUser,
    db: FirebaseFirestore,
    context: Activity,
    newName: String,
    newEmail: String,
    newPassword: String,
    newDateOfBirth: String,
    onComplete: () -> Unit
) {
    // 1. Preparamos el mapa de datos
    val updatedUserMap = hashMapOf<String, Any>(
        "name" to newName,
        "email" to newEmail,
        "dateOfBirth" to newDateOfBirth
    )

    // 2. Guardar en Firestore
    db.collection("users").document(user.uid)
        .set(updatedUserMap, SetOptions.merge())
        .addOnSuccessListener {
            // Firestore OK, ahora Auth
            updateAuthCredentials(user, newEmail, newPassword, context, onComplete)
        }
        .addOnFailureListener { e ->
            Toast.makeText(context, "Error al guardar datos: ${e.message}", Toast.LENGTH_SHORT).show()
            onComplete()
        }
}

private fun updateAuthCredentials(
    user: FirebaseUser,
    newEmail: String,
    newPassword: String,
    context: Activity,
    onComplete: () -> Unit
) {
    var updatesCount = 0
    var errors = mutableListOf<String>()

    fun checkComplete() {
        if (updatesCount == 0) {
            if (errors.isEmpty()) {
                Toast.makeText(context, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Datos guardados, pero con alertas: ${errors.joinToString()}", Toast.LENGTH_LONG).show()
            }
            onComplete()
        }
    }

    // Actualizar Email
    if (user.email != newEmail && newEmail.isNotEmpty()) {
        updatesCount++
        user.verifyBeforeUpdateEmail(newEmail)
            .addOnSuccessListener {
                Toast.makeText(context, "Verifica tu correo para confirmar el nuevo email.", Toast.LENGTH_LONG).show()
                updatesCount--
                checkComplete()
            }
            .addOnFailureListener {
                errors.add("Email: ${it.message}")
                updatesCount--
                checkComplete()
            }
    }

    // Actualizar Contraseña
    if (newPassword.isNotEmpty()) {
        updatesCount++
        user.updatePassword(newPassword)
            .addOnSuccessListener {
                Toast.makeText(context, "Contraseña actualizada.", Toast.LENGTH_SHORT).show()
                updatesCount--
                checkComplete()
            }
            .addOnFailureListener {
                errors.add("Pass: Requiere login reciente.")
                updatesCount--
                checkComplete()
            }
    }

    checkComplete()
}