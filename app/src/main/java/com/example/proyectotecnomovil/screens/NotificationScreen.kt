package com.example.proyectotecnomovil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectotecnomovil.data.local.NotificationEntity
import com.example.proyectotecnomovil.viewmodel.NotificationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavController,
    onBack: () -> Unit,
    viewModel: NotificationViewModel = viewModel()
) {
    val notificaciones by viewModel.historialNotificaciones.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        if (notificaciones.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("No tienes notificaciones nuevas", color = Color.Gray)
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
                items(notificaciones) { notificacion ->
                    NotificationItem(
                        notificacion = notificacion,
                        // Pasamos la acción de borrar al item
                        onDeleteClick = { viewModel.eliminarNotificacion(notificacion) }
                    )
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                }
            }
        }
    }
}

@Composable
fun NotificationItem(
    notificacion: NotificationEntity,
    onDeleteClick: () -> Unit // Callback para borrar
) {
    val fechaFormat = SimpleDateFormat("HH:mm dd/MM", Locale.getDefault())
    val fechaTexto = try {
        fechaFormat.format(Date(notificacion.fecha))
    } catch (e: Exception) { "error al parsear fecha" }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (notificacion.leida) Color.White else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            modifier = Modifier.size(48.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Textos
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notificacion.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Botón de borrar (Cruz)
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Borrar notificación",
                        tint = Color.Gray
                    )
                }
            }

            Text(
                text = fechaTexto,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = notificacion.mensaje,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                maxLines = 3
            )
        }
    }
}