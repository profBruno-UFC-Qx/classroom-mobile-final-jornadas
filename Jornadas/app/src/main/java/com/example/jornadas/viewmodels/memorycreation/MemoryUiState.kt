package com.example.jornadas.viewmodels.memorycreation

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MemoryUiState(
    val title: String = "",
    val description: String = "",
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
    val location: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val imageUri: String? = null,
    val isSaved: Boolean = false
)
