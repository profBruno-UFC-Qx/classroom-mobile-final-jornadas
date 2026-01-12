package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.jornadas.ui.theme.backgroundDark
import com.example.jornadas.ui.theme.backgroundDarkHighContrast
import com.example.jornadas.ui.theme.backgroundDarkMediumContrast
import com.example.jornadas.ui.theme.backgroundLight
import com.example.jornadas.ui.theme.backgroundLightHighContrast
import com.example.jornadas.ui.theme.backgroundLightMediumContrast
import com.example.jornadas.ui.theme.errorContainerDark
import com.example.jornadas.ui.theme.errorContainerDarkHighContrast
import com.example.jornadas.ui.theme.errorContainerDarkMediumContrast
import com.example.jornadas.ui.theme.errorContainerLight
import com.example.jornadas.ui.theme.errorContainerLightHighContrast
import com.example.jornadas.ui.theme.errorContainerLightMediumContrast
import com.example.jornadas.ui.theme.errorDark
import com.example.jornadas.ui.theme.errorDarkHighContrast
import com.example.jornadas.ui.theme.errorDarkMediumContrast
import com.example.jornadas.ui.theme.errorLight
import com.example.jornadas.ui.theme.errorLightHighContrast
import com.example.jornadas.ui.theme.errorLightMediumContrast
import com.example.jornadas.ui.theme.inverseOnSurfaceDark
import com.example.jornadas.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.jornadas.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.jornadas.ui.theme.inverseOnSurfaceLight
import com.example.jornadas.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.jornadas.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.jornadas.ui.theme.inversePrimaryDark
import com.example.jornadas.ui.theme.inversePrimaryDarkHighContrast
import com.example.jornadas.ui.theme.inversePrimaryDarkMediumContrast
import com.example.jornadas.ui.theme.inversePrimaryLight
import com.example.jornadas.ui.theme.inversePrimaryLightHighContrast
import com.example.jornadas.ui.theme.inversePrimaryLightMediumContrast
import com.example.jornadas.ui.theme.inverseSurfaceDark
import com.example.jornadas.ui.theme.inverseSurfaceDarkHighContrast
import com.example.jornadas.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.jornadas.ui.theme.inverseSurfaceLight
import com.example.jornadas.ui.theme.inverseSurfaceLightHighContrast
import com.example.jornadas.ui.theme.inverseSurfaceLightMediumContrast
import com.example.jornadas.ui.theme.onBackgroundDark
import com.example.jornadas.ui.theme.onBackgroundDarkHighContrast
import com.example.jornadas.ui.theme.onBackgroundDarkMediumContrast
import com.example.jornadas.ui.theme.onBackgroundLight
import com.example.jornadas.ui.theme.onBackgroundLightHighContrast
import com.example.jornadas.ui.theme.onBackgroundLightMediumContrast
import com.example.jornadas.ui.theme.onErrorContainerDark
import com.example.jornadas.ui.theme.onErrorContainerDarkHighContrast
import com.example.jornadas.ui.theme.onErrorContainerDarkMediumContrast
import com.example.jornadas.ui.theme.onErrorContainerLight
import com.example.jornadas.ui.theme.onErrorContainerLightHighContrast
import com.example.jornadas.ui.theme.onErrorContainerLightMediumContrast
import com.example.jornadas.ui.theme.onErrorDark
import com.example.jornadas.ui.theme.onErrorDarkHighContrast
import com.example.jornadas.ui.theme.onErrorDarkMediumContrast
import com.example.jornadas.ui.theme.onErrorLight
import com.example.jornadas.ui.theme.onErrorLightHighContrast
import com.example.jornadas.ui.theme.onErrorLightMediumContrast
import com.example.jornadas.ui.theme.onPrimaryContainerDark
import com.example.jornadas.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.jornadas.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.jornadas.ui.theme.onPrimaryContainerLight
import com.example.jornadas.ui.theme.onPrimaryContainerLightHighContrast
import com.example.jornadas.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.jornadas.ui.theme.onPrimaryDark
import com.example.jornadas.ui.theme.onPrimaryDarkHighContrast
import com.example.jornadas.ui.theme.onPrimaryDarkMediumContrast
import com.example.jornadas.ui.theme.onPrimaryLight
import com.example.jornadas.ui.theme.onPrimaryLightHighContrast
import com.example.jornadas.ui.theme.onPrimaryLightMediumContrast
import com.example.jornadas.ui.theme.onSecondaryContainerDark
import com.example.jornadas.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.jornadas.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.jornadas.ui.theme.onSecondaryContainerLight
import com.example.jornadas.ui.theme.onSecondaryContainerLightHighContrast
import com.example.jornadas.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.jornadas.ui.theme.onSecondaryDark
import com.example.jornadas.ui.theme.onSecondaryDarkHighContrast
import com.example.jornadas.ui.theme.onSecondaryDarkMediumContrast
import com.example.jornadas.ui.theme.onSecondaryLight
import com.example.jornadas.ui.theme.onSecondaryLightHighContrast
import com.example.jornadas.ui.theme.onSecondaryLightMediumContrast
import com.example.jornadas.ui.theme.onSurfaceDark
import com.example.jornadas.ui.theme.onSurfaceDarkHighContrast
import com.example.jornadas.ui.theme.onSurfaceDarkMediumContrast
import com.example.jornadas.ui.theme.onSurfaceLight
import com.example.jornadas.ui.theme.onSurfaceLightHighContrast
import com.example.jornadas.ui.theme.onSurfaceLightMediumContrast
import com.example.jornadas.ui.theme.onSurfaceVariantDark
import com.example.jornadas.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.jornadas.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.jornadas.ui.theme.onSurfaceVariantLight
import com.example.jornadas.ui.theme.onSurfaceVariantLightHighContrast
import com.example.jornadas.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.jornadas.ui.theme.onTertiaryContainerDark
import com.example.jornadas.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.jornadas.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.jornadas.ui.theme.onTertiaryContainerLight
import com.example.jornadas.ui.theme.onTertiaryContainerLightHighContrast
import com.example.jornadas.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.jornadas.ui.theme.onTertiaryDark
import com.example.jornadas.ui.theme.onTertiaryDarkHighContrast
import com.example.jornadas.ui.theme.onTertiaryDarkMediumContrast
import com.example.jornadas.ui.theme.onTertiaryLight
import com.example.jornadas.ui.theme.onTertiaryLightHighContrast
import com.example.jornadas.ui.theme.onTertiaryLightMediumContrast
import com.example.jornadas.ui.theme.outlineDark
import com.example.jornadas.ui.theme.outlineDarkHighContrast
import com.example.jornadas.ui.theme.outlineDarkMediumContrast
import com.example.jornadas.ui.theme.outlineLight
import com.example.jornadas.ui.theme.outlineLightHighContrast
import com.example.jornadas.ui.theme.outlineLightMediumContrast
import com.example.jornadas.ui.theme.outlineVariantDark
import com.example.jornadas.ui.theme.outlineVariantDarkHighContrast
import com.example.jornadas.ui.theme.outlineVariantDarkMediumContrast
import com.example.jornadas.ui.theme.outlineVariantLight
import com.example.jornadas.ui.theme.outlineVariantLightHighContrast
import com.example.jornadas.ui.theme.outlineVariantLightMediumContrast
import com.example.jornadas.ui.theme.primaryContainerDark
import com.example.jornadas.ui.theme.primaryContainerDarkHighContrast
import com.example.jornadas.ui.theme.primaryContainerDarkMediumContrast
import com.example.jornadas.ui.theme.primaryContainerLight
import com.example.jornadas.ui.theme.primaryContainerLightHighContrast
import com.example.jornadas.ui.theme.primaryContainerLightMediumContrast
import com.example.jornadas.ui.theme.primaryDark
import com.example.jornadas.ui.theme.primaryDarkHighContrast
import com.example.jornadas.ui.theme.primaryDarkMediumContrast
import com.example.jornadas.ui.theme.primaryLight
import com.example.jornadas.ui.theme.primaryLightHighContrast
import com.example.jornadas.ui.theme.primaryLightMediumContrast
import com.example.jornadas.ui.theme.scrimDark
import com.example.jornadas.ui.theme.scrimDarkHighContrast
import com.example.jornadas.ui.theme.scrimDarkMediumContrast
import com.example.jornadas.ui.theme.scrimLight
import com.example.jornadas.ui.theme.scrimLightHighContrast
import com.example.jornadas.ui.theme.scrimLightMediumContrast
import com.example.jornadas.ui.theme.secondaryContainerDark
import com.example.jornadas.ui.theme.secondaryContainerDarkHighContrast
import com.example.jornadas.ui.theme.secondaryContainerDarkMediumContrast
import com.example.jornadas.ui.theme.secondaryContainerLight
import com.example.jornadas.ui.theme.secondaryContainerLightHighContrast
import com.example.jornadas.ui.theme.secondaryContainerLightMediumContrast
import com.example.jornadas.ui.theme.secondaryDark
import com.example.jornadas.ui.theme.secondaryDarkHighContrast
import com.example.jornadas.ui.theme.secondaryDarkMediumContrast
import com.example.jornadas.ui.theme.secondaryLight
import com.example.jornadas.ui.theme.secondaryLightHighContrast
import com.example.jornadas.ui.theme.secondaryLightMediumContrast
import com.example.jornadas.ui.theme.surfaceBrightDark
import com.example.jornadas.ui.theme.surfaceBrightDarkHighContrast
import com.example.jornadas.ui.theme.surfaceBrightDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceBrightLight
import com.example.jornadas.ui.theme.surfaceBrightLightHighContrast
import com.example.jornadas.ui.theme.surfaceBrightLightMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerDark
import com.example.jornadas.ui.theme.surfaceContainerDarkHighContrast
import com.example.jornadas.ui.theme.surfaceContainerDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerHighDark
import com.example.jornadas.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.jornadas.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerHighLight
import com.example.jornadas.ui.theme.surfaceContainerHighLightHighContrast
import com.example.jornadas.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerHighestDark
import com.example.jornadas.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.jornadas.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerHighestLight
import com.example.jornadas.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.jornadas.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerLight
import com.example.jornadas.ui.theme.surfaceContainerLightHighContrast
import com.example.jornadas.ui.theme.surfaceContainerLightMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerLowDark
import com.example.jornadas.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.jornadas.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerLowLight
import com.example.jornadas.ui.theme.surfaceContainerLowLightHighContrast
import com.example.jornadas.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerLowestDark
import com.example.jornadas.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.jornadas.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceContainerLowestLight
import com.example.jornadas.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.jornadas.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.jornadas.ui.theme.surfaceDark
import com.example.jornadas.ui.theme.surfaceDarkHighContrast
import com.example.jornadas.ui.theme.surfaceDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceDimDark
import com.example.jornadas.ui.theme.surfaceDimDarkHighContrast
import com.example.jornadas.ui.theme.surfaceDimDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceDimLight
import com.example.jornadas.ui.theme.surfaceDimLightHighContrast
import com.example.jornadas.ui.theme.surfaceDimLightMediumContrast
import com.example.jornadas.ui.theme.surfaceLight
import com.example.jornadas.ui.theme.surfaceLightHighContrast
import com.example.jornadas.ui.theme.surfaceLightMediumContrast
import com.example.jornadas.ui.theme.surfaceVariantDark
import com.example.jornadas.ui.theme.surfaceVariantDarkHighContrast
import com.example.jornadas.ui.theme.surfaceVariantDarkMediumContrast
import com.example.jornadas.ui.theme.surfaceVariantLight
import com.example.jornadas.ui.theme.surfaceVariantLightHighContrast
import com.example.jornadas.ui.theme.surfaceVariantLightMediumContrast
import com.example.jornadas.ui.theme.tertiaryContainerDark
import com.example.jornadas.ui.theme.tertiaryContainerDarkHighContrast
import com.example.jornadas.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.jornadas.ui.theme.tertiaryContainerLight
import com.example.jornadas.ui.theme.tertiaryContainerLightHighContrast
import com.example.jornadas.ui.theme.tertiaryContainerLightMediumContrast
import com.example.jornadas.ui.theme.tertiaryDark
import com.example.jornadas.ui.theme.tertiaryDarkHighContrast
import com.example.jornadas.ui.theme.tertiaryDarkMediumContrast
import com.example.jornadas.ui.theme.tertiaryLight
import com.example.jornadas.ui.theme.tertiaryLightHighContrast
import com.example.jornadas.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun JornadasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

