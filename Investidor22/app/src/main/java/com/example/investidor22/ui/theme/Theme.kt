package com.example.investidor22.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Green80, // Verde Escuro
    secondary = Green40, // Verde Claro
    tertiary = Green60, // Verde Vibrante
    background = Black, // Fundo Preto
    surface = Green80, // Fundo para Superfícies
    onPrimary = White, // Texto Branco
    onSecondary = White, // Texto Branco
    onBackground = White, // Texto Branco
    onSurface = White // Texto Branco
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50), // Verde Claro
    onPrimary = Color.White, // Texto Branco em cima do verde
    background = Color(0xFFFFFFFF), // Fundo Branco
    surface = Color(0xFFFFFFFF), // Fundo Branco
    onSecondary = Color.Black, // Texto Preto
    onSurface = Color.Black // Texto Preto
)




@Composable
fun Investidor22Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
