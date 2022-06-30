package com.canbus.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.canbus.app.ui.*

private val CanbusThemeLight = lightColors(
    primary = SlateBlue,
    onPrimary = Color.White,
    primaryVariant = SlateBlueVariant,
    secondary = yellow500,
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val CanbusThemeDark = darkColors(
    primary = SlateBlue,
    secondary = yellow200,
    primaryVariant = SlateBlueVariant,
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun CanbusAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        CanbusThemeDark
    } else {
        CanbusThemeLight
    }
    AppTheme(darkTheme, colors, content)
}

@Composable
fun AppTheme(
    darkTheme: Boolean,
    colors: Colors,
    content: @Composable () -> Unit
) {
    val elevation = if (darkTheme) DarkElevation else LightElevation
    CompositionLocalProvider(
        LocalElevations provides elevation,
        LocalSpacing provides Spacing(),
        LocalSizes provides Sizes()
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