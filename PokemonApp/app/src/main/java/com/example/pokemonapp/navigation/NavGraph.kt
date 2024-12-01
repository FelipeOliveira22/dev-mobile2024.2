package com.example.pokemonapp.navigation


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapp.ui.theme.screens.PokemonScreen
import com.example.pokemonapp.models.getMockedPokemonList
import com.example.pokemonapp.ui.theme.screens.HomeScreen
@ExperimentalMaterial3Api
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val pokemonList = remember { mutableStateListOf(*getMockedPokemonList().toTypedArray()) }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPokemonSelected = { pokemon ->
                    navController.navigate("pokemon/${pokemon.name}")
                },
                pokemonList = pokemonList
            )
        }
        composable("pokemon/{pokemon}") { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemon")
            val selectedPokemon = pokemonList.first { it.name == pokemonName }
            PokemonScreen(
                pokemon = selectedPokemon,
                onFavoriteChanged = { updatedPokemon ->
                    val index = pokemonList.indexOfFirst { it.id == updatedPokemon.id }
                    if (index >= 0) {
                        pokemonList[index] = updatedPokemon
                    }
                    navController.popBackStack()
                }
            )
        }
    }
}
