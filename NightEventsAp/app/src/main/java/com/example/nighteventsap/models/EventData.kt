package com.example.nighteventsap.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.nighteventsap.R

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val isFavorite: MutableState<Boolean> = mutableStateOf(false),
    val isSubscribed: MutableState<Boolean> = mutableStateOf(false),
    val imageRes: Int
)

val eventList = listOf(
    Event(
        id = 1,
        title = "Conferência de Tecnologia",
        description = "Tendências em tecnologia.",
        date = "2024-12-15",
        location = "Parque Tecnológico",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img1
    ),
    Event(
        id = 2,
        title = "Feira de Tecnologia ",
        description = "Exposição de projetos científicos.",
        date = "2025-01-14",
        location = "Centro de Eventos do Ceará",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img2
    ),
    Event(
        id = 3,
        title = "Workshop de Desenvolvimento Mobile",
        description = "Desenvolvimento de aplicações.",
        date = "2024-10-04",
        location = "Universidade Federal do Ceará",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img3
    ),
    Event(
        id = 4,
        title = "Hackathon - Desafios em IA",
        description = "Resolva problemas reais usando inteligência artificial.",
        date = "2024-12-25",
        location = "Universidade Federal do Ceará",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img4
    ),
    Event(
        id = 5,
        title = "Evento de Engenharia da Computação e Engenharia de Software",
        description = "Palestras com especialistas sobre segurança digital.",
        date = "2024-12-14",
        location = "Campus TI",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img5
    )
)
