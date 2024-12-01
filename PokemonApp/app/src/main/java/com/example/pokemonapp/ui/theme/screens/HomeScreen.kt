package com.example.pokemonapp.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokemonapp.models.Pokemon
import com.example.pokemonapp.models.getMockedPokemonList
import com.example.pokemonapp.ui.theme.components.PokemonListItem

@Composable
fun HomeScreen(onPokemonSelected: (Pokemon) -> Unit, pokemonList: List<Pokemon>) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredPokemonList = remember(searchQuery) {
        pokemonList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pesquisar Pokémon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(filteredPokemonList) { pokemon ->
                PokemonListItem(pokemon = pokemon, onPokemonSelected = onPokemonSelected)
            }
        }
    }
}
