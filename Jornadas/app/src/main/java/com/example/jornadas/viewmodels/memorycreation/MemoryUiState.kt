package com.example.jornadas.viewmodels.memorycreation

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MemoryUiState(
    val title: String = "",
    val description: String = "",
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
    val location: String = "",
    val imageUri: String? = null,
    val isSaved: Boolean = false
)
