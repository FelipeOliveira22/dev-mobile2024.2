package com.example.pokemonapp.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokemonapp.models.Pokemon


@Composable
fun PokemonListItem(pokemon: Pokemon, onPokemonSelected: (Pokemon) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPokemonSelected(pokemon) }
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = pokemon.imageRes),
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = if (pokemon.isFavorite) "❤️ Favorito" else "☆ Não Favorito",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (pokemon.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
