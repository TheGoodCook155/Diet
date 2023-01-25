package com.diet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = primary,
    primaryVariant = lightPrimary,
    secondary = Teal200

    /*
val lightPrimary = Color(0xFFc8e6c9)
val primary = Color(0xFF4caf50)
val textIcons = Color(0xFFFFFFFF)
val accent = Color(0xFFcddc39)
val primaryText = Color(0xFF212121)
val secondaryText = Color(0xff757575)
val dividerColor = Color(0xFFBDBDBD)
     */

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DietTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}