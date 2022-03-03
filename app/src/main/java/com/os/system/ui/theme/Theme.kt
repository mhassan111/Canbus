package com.os.system.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.os.system.ui.*

private val LightColorPalette = lightColors(
    primary = ClayColor,
    primaryVariant = ClayColor,
    secondary = ClayColor
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black
)

private val TOSThemeLight = lightColors(
    primary = ClayColor,
    onPrimary = Color.White,
    primaryVariant = ClayVariant,
    secondary = yellow500
)

private val TOSThemeDark = darkColors(
    primary = ClayColor,
    secondary = yellow200,
    surface = ClayDark
)

@Composable
fun TOSTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        TOSThemeDark
    } else {
        TOSThemeLight
    }
    TOSAppTheme(darkTheme, colors, content)
}

@Composable
fun TOSAppTheme(
    darkTheme: Boolean,
    colors: Colors,
    content: @Composable () -> Unit
) {
    val elevation = if (darkTheme) DarkElevation else LightElevation
    CompositionLocalProvider(
        LocalElevations provides elevation,
        LocalSpacing provides Spacing(), LocalSizes provides Sizes()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

private val LightElevation = Elevations()
private val DarkElevation = Elevations(card = 1.dp)