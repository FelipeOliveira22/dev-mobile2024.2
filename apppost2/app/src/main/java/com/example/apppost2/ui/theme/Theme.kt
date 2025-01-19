package com.example.apppost2.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4CAF50), // Verde
    onPrimary = Color.White, // Texto em cima do verde
    secondary = Color(0xFF388E3C),
    onSecondary = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50), // Verde
    onPrimary = Color.White, // Texto em cima do verde
    secondary = Color(0xFF388E3C),
    onSecondary = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
)

@Composable
fun Apppost2Theme(
    darkTheme: Boolean = false, // Substitua por `isSystemInDarkTheme()` se quiser usar tema dinâmico
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
