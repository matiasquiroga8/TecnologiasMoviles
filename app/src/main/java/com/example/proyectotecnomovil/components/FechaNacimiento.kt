package com.example.proyectotecnomovil.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun FechaNacimientoField(
    dateOfBirth: String,
    onDateSelected: (String) -> Unit
) {
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = dateOfBirth,
        onValueChange = {
            onDateSelected(it)
            isError = !isValidDate(it)
        },
        placeholder = { Text("dd/MM/yyyy") },
        label = { Text("Fecha de nacimiento") },
        modifier = Modifier.fillMaxWidth(),
        isError = isError,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            cursorColor = Color.Black,
            focusedBorderColor = if (isError) Color.Red else Color(0xFF4CAF50),
            unfocusedBorderColor = if (isError) Color.Red else Color.Gray
        )
    )

    if (isError) {
        Text(
            text = "Formato inválido. Use dd/MM/yyyy",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}

fun isValidDate(input: String): Boolean {
    return try {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formatter.isLenient = false
        formatter.parse(input)
        true
    } catch (e: Exception) {
        false
    }
}