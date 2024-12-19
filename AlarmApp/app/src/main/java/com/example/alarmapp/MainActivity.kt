package com.example.alarmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.alarmapp.ui.theme.AlarmAppTheme
import com.example.alarmapp.ui.theme.components.requestNotificationPermission
import com.example.alarmapp.ui.theme.screens.AlarmScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission(this)
        setContent {
            AlarmAppTheme {
                AlarmScreen()
            }
        }
    }
}
