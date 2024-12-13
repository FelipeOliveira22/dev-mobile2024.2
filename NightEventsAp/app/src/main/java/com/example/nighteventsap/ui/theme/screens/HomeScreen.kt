package com.example.nighteventsap.ui.theme.screens

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.nighteventsap.models.eventList

@Composable
fun HomeScreen(navController: NavHostController, context: Context) {
    // Cria o canal de notificação
    createNotificationChannel(context)

    // Verifica e solicita permissão para notificações
    val activity = context as? ComponentActivity
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
        }
    }

    // Exibe os eventos
    Column(modifier = Modifier.padding(16.dp)) {
        // Exibe eventos inscritos
        val subscribedEvents = eventList.filter { it.isSubscribed.value }
        if (subscribedEvents.isNotEmpty()) {
            Text(
                text = "Eventos Inscritos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(subscribedEvents) { event ->
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {
                                navController.navigate("eventDetails/${event.id}")
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = event.imageRes),
                            contentDescription = event.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(eventList) { event ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = event.imageRes),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .padding(end = 16.dp)
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = event.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = event.location,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Icon(
                                imageVector = if (event.isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.clickable {
                                    event.isFavorite.value = !event.isFavorite.value
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = event.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    event.isSubscribed.value = !event.isSubscribed.value
                                    if (event.isSubscribed.value) {
                                        sendNotification(context, event.title)
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = if (event.isSubscribed.value) "Inscrito" else "Inscrever-se")
                            }
                            Button(
                                onClick = { navController.navigate("eventDetails/${event.id}") },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary
                                )
                            ) {
                                Text(text = "Ver mais sobre ${event.title}")
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Inscrição de Eventos"
        val descriptionText = "Canal para notificações de inscrição em eventos"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("EVENT_CHANNEL", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

private fun sendNotification(context: Context, eventTitle: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        println("Permissão de notificação não concedida.")
        return
    }

    val builder = NotificationCompat.Builder(context, "EVENT_CHANNEL")
        .setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle("Inscrição Confirmada")
        .setContentText("Você foi inscrito no evento: $eventTitle")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        notify(eventTitle.hashCode(), builder.build())
    }
}
