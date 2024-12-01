package com.example.pokemonapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokemonapp.models.Pokemon
@ExperimentalMaterial3Api
@Composable
fun PokemonScreen(pokemon: Pokemon, onFavoriteChanged: (Pokemon) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(pokemon.name) })
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = pokemon.imageRes),
                contentDescription = "${pokemon.name} Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = pokemon.type,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = pokemon.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Habilidades: ${pokemon.abilities}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onFavoriteChanged(pokemon.copy(isFavorite = !pokemon.isFavorite)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (pokemon.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = if (pokemon.isFavorite) "Remover dos Favoritos" else "Adicionar aos Favoritos"
                )
            }
        }
    }
}
