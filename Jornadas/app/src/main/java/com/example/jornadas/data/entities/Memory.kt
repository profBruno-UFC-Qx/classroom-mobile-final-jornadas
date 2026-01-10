package com.example.jornadas.data.entities

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "memories")
data class Memory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: String,
    val title: String,
    val description: String,
    val date: LocalDate,
    val location: String,
    val imageUri: String? = null
)