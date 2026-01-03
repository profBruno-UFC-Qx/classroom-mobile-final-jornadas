package com.example.jornadas.data

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Memory(
    val id: Int,
    val title: String,
    val description: String,
    val date: LocalDate,
    val location: String,
    @DrawableRes val imageRes: Int
)

