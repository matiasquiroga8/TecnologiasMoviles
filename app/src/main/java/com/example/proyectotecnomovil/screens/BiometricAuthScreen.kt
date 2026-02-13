package com.example.proyectotecnomovil.screens

import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@Composable
fun BiometricAuthScreen(
    onAuthenticated: () -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity

    // 1. CAMBIO: Definimos que aceptamos Huella (STRONG) O Pin/Patrón (DEVICE_CREDENTIAL)
    val authenticators = BIOMETRIC_STRONG or DEVICE_CREDENTIAL

    // Configuración del diálogo
    val promptInfo = remember {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación requerida")
            .setSubtitle("Usa tu huella o el PIN del dispositivo")
            .setAllowedAuthenticators(authenticators)
            // 2. CAMBIO: ¡IMPORTANTE! Eliminamos setNegativeButtonText.
            // Android pone su propio botón de cancelar cuando se usa DEVICE_CREDENTIAL.
            // .setNegativeButtonText("Cancelar") <--- ESTO SE BORRA O SE COMENTA
            .build()
    }

    LaunchedEffect(Unit) {
        if (activity != null) {
            val biometricManager = BiometricManager.from(context)

            // 3. Verificamos si podemos usar cualquiera de los dos métodos
            when (biometricManager.canAuthenticate(authenticators)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    // Todo listo, lanzamos el prompt
                    val executor = ContextCompat.getMainExecutor(context)
                    val biometricPrompt = BiometricPrompt(activity, executor,
                        object : BiometricPrompt.AuthenticationCallback() {
                            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                                super.onAuthenticationSucceeded(result)
                                onAuthenticated()
                            }

                            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                                super.onAuthenticationError(errorCode, errString)
                                // Si el usuario cancela a propósito o falla mucho
                                if (errorCode == BiometricPrompt.ERROR_USER_CANCELED ||
                                    errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                                    onCancel()
                                } else {
                                    // Otros errores (como no configurado), mostramos mensaje pero quizás queramos dejar pasar en debug
                                    Toast.makeText(context, "Error: $errString", Toast.LENGTH_SHORT).show()
                                    onCancel()
                                }
                            }

                            override fun onAuthenticationFailed() {
                                super.onAuthenticationFailed()
                                // Huella incorrecta, Android lo maneja solo (vibra y pide intento de nuevo)
                            }
                        }
                    )
                    biometricPrompt.authenticate(promptInfo)
                }

                // CASOS DE ERROR DE CONFIGURACIÓN DEL TELÉFONO
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE,
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE,
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    // Si el teléfono NO tiene huella NI PIN configurado:
                    // En un caso real, obligarías al usuario a configurarlo.
                    // Para tu tesis/debug, te recomiendo dejarlo pasar:
                    Toast.makeText(context, "Dispositivo sin seguridad. Accediendo...", Toast.LENGTH_LONG).show()
                    onAuthenticated()
                }

                else -> {
                    // Cualquier otro error raro
                    onCancel()
                }
            }
        } else {
            // Si por alguna razón no hay actividad (raro)
            onCancel()
        }
    }

    // Pantalla de carga mientras carga el diálogo de seguridad
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}