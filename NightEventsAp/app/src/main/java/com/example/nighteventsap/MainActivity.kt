package com.example.nighteventsap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nighteventsap.ui.theme.NightEventsAppTheme
import com.example.nighteventsap.ui.theme.components.DrawerContent
import com.example.nighteventsap.ui.theme.components.TopBar
import com.example.nighteventsap.ui.theme.components.BottomNavigationBar
import com.example.nighteventsap.ui.theme.screens.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val isDarkTheme = remember { mutableStateOf(false) } // Controle do tema

            NightEventsAppTheme(darkTheme = isDarkTheme.value) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = true,
                    drawerContent = {
                        DrawerContent(navController) {
                            // Ações adicionais no DrawerContent
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onThemeToggle = { isDarkTheme.value = !isDarkTheme.value },
                                onOpenDrawer = { scope.launch { drawerState.open() } }
                            )
                        },
                        bottomBar = { BottomNavigationBar(navController) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("home") {
                                HomeScreen(navController, this@MainActivity) // Passando o Context
                            }
                            composable("events") {
                                SubscribedEventsScreen(navController)
                            }
                            composable("favorites") {
                                FavoritesScreen(navController)
                            }
                            composable("eventDetails/{eventId}") { backStackEntry ->
                                val eventId = backStackEntry.arguments?.getString("eventId")
                                EventDetailsScreen(eventId = eventId?.toIntOrNull())
                            }
                        }
                    }
                }
            }
        }
    }
}
