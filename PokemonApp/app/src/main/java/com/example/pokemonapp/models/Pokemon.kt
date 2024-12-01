package com.example.pokemonapp.models

import com.example.pokemonapp.R

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val imageRes: Int,
    val description: String,
    val abilities: String,
    var isFavorite: Boolean = false
)

fun getMockedPokemonList(): List<Pokemon> {
    return listOf(
        Pokemon(
            id = 1,
            name = "Pikachu",
            type = "Electric",
            imageRes = R.drawable.pikachu,
            description = "Pikachu é um Pokémon do tipo elétrico que armazena eletricidade em suas bochechas.",
            abilities = "Choque do Trovão, Cabeçada",
            isFavorite = false
        ),
        Pokemon(
            id = 2,
            name = "Charmander",
            type = "Fire",
            imageRes = R.drawable.charmander,
            description = "Charmander é um Pokémon do tipo fogo com uma chama na ponta de sua cauda.",
            abilities = "Lança-chamas, Garra de Dragão",
            isFavorite = false
        ),
        Pokemon(
            id = 3,
            name = "Squirtle",
            type = "Water",
            imageRes = R.drawable.skirdle,
            description = "Squirtle é um Pokémon do tipo água que usa sua concha para se proteger.",
            abilities = "Jato de Água, Giro Rápido",
            isFavorite = false
        ),
        Pokemon(
            id = 4,
            name = "Bulbasaur",
            type = "Grass/Poison",
            imageRes = R.drawable.bulbasaur,
            description = "Bulbasaur é um Pokémon do tipo grama/veneno que possui uma semente em suas costas.",
            abilities = "Chicote de Vinha, Folha Navalha",
            isFavorite = false
        ),
        Pokemon(
            id = 5,
            name = "Jigglypuff",
            type = "Fairy",
            imageRes = R.drawable.jigglypuff,
            description = "Jigglypuff é um Pokémon do tipo fada que usa sua canção para colocar os inimigos para dormir.",
            abilities = "Canção, Corpo a Corpo",
            isFavorite = false
        )
    )
}


