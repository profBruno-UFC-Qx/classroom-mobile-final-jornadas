package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.example.jornadas.R

val playfair = FontFamily(
    Font(R.font.playfairdisplaysc_regular, FontWeight.Normal),
        Font(R.font.playfairdisplaysc_bold, FontWeight.Bold)
)

val quicksand = FontFamily(
    Font(R.font.quicksand_regular),
    Font(R.font.quicksand_bold)
)


// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    headlineLarge = baseline.headlineLarge.copy(
        fontFamily = playfair,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    headlineMedium = baseline.headlineMedium.copy(
        fontFamily = playfair,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    labelLarge = baseline.labelLarge.copy(
        fontFamily = quicksand,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    labelMedium = baseline.labelMedium.copy(
        fontFamily = quicksand,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    labelSmall = baseline.labelSmall.copy(
        fontFamily = quicksand,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

)

