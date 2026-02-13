package com.example.proyectotecnomovil.security

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

// 1. ELIMINAMOS @RequiresApi. La librería androidx lo maneja sola.
fun canAuthenticate(context: Context): Boolean {
    val biometricManager = BiometricManager.from(context)
    // Usamos BIOMETRIC_STRONG para asegurar compatibilidad básica
    return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
}

fun showBiometricPrompt(
    activity: FragmentActivity,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val executor = ContextCompat.getMainExecutor(activity)

    val biometricPrompt = BiometricPrompt(
        activity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onSuccess()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // Ignoramos el error si el usuario canceló voluntariamente (botón atrás)
                if (errorCode != BiometricPrompt.ERROR_USER_CANCELED &&
                    errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    onError(errString.toString())
                } else {
                    // Si cancela, también llamamos a onError para navegar al Login
                    onError("Cancelado por usuario")
                }
            }
        }
    )

    // Configuración del diálogo
    val promptInfoBuilder = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Confirmá tu identidad")
        .setSubtitle("Ingresa a Manos Locales")

    // Lógica para permitir PIN/Patrón según versión de Android
    // DEVICE_CREDENTIAL solo funciona bien en Android 11+ (API 30) con esta librería a veces.
    // Para asegurar que no falle en versiones viejas (API 28/29), usamos el botón negativo.

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // Android 11+: Podemos permitir huella O patrón
        promptInfoBuilder.setAllowedAuthenticators(
            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )
    } else {
        // Android 10 o inferior: Usamos solo Huella y ponemos botón "Cancelar"
        promptInfoBuilder.setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
        promptInfoBuilder.setNegativeButtonText("Usar contraseña")
    }

    biometricPrompt.authenticate(promptInfoBuilder.build())
}