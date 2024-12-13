package com.example.nighteventsap.ui.theme.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(navController: NavHostController, onSendNotification: () -> Unit) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Menu",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            MenuItem(
                text = "Perfil",
                color = MaterialTheme.colorScheme.primary,
                onClick = { navController.navigate("profile") },
                icon = Icons.Default.Person
            )
            MenuItem(
                text = "Notificações",
                color = MaterialTheme.colorScheme.secondary,
                onClick = onSendNotification,
                icon = Icons.Default.Notifications
            )
            MenuItem(
                text = "Sair",
                color = MaterialTheme.colorScheme.error,
                onClick = { (context as? Activity)?.finishAffinity() },
                icon = Icons.Default.ExitToApp
            )

            Spacer(modifier = Modifier.weight(1f)) // Espaço flexível para empurrar o rodapé para baixo

            Text(
                text = "Versão 1.0.0",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun MenuItem(
    text: String,
    onClick: () -> Unit,
    color: androidx.compose.ui.graphics.Color,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = color,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
