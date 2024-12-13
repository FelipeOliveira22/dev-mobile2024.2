package com.example.startservice

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : ComponentActivity() {
    private val controlChannelId = "Service_control_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createControlNotificationChannel()
        requestNotificationPermission()

        setContent {
            ServiceControlScreen(
                onStartClick = { startBackgroundService() },
                onStopClick = { stopBackgroundService() }
            )
        }
    }

    private fun startBackgroundService() {
        val intent = Intent(this, BackgroundService::class.java)
        startService(intent)
        Toast.makeText(this, "Serviço iniciado", Toast.LENGTH_SHORT).show()
        showControlNotification("Serviço em Execução")
    }

    private fun stopBackgroundService() {
        val intent = Intent(this, BackgroundService::class.java)
        stopService(intent)
        Toast.makeText(this, "Solicitação para parar o serviço", Toast.LENGTH_SHORT).show()
        showControlNotification("Serviço parado")
    }

    @SuppressLint("MissingPermission")
    private fun showControlNotification(message: String) {
        val notification = NotificationCompat.Builder(this, controlChannelId)
            .setContentTitle("Serviço de Controle")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(this).notify(System.currentTimeMillis().toInt(), notification)
    }

    private fun createControlNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                controlChannelId,
                "Controle de Serviço",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        Toast.makeText(this, "Permissão concedida", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show()
                    }
                }

            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
