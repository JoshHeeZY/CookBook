package com.joshuahee.cookbook.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.joshuahee.cookbook.data.Difficulty

private val LightColorScheme = lightColorScheme(
    primary = Paprika40,
    onPrimary = Color.White,
    primaryContainer = Paprika90,
    onPrimaryContainer = Paprika10,
    secondary = Clay40,
    onSecondary = Color.White,
    secondaryContainer = Clay90,
    onSecondaryContainer = Paprika10,
    tertiary = Olive40,
    onTertiary = Color.White,
    tertiaryContainer = Olive90,
    background = Cream,
    surface = Cream
)

private val DarkColorScheme = darkColorScheme(
    primary = Paprika80,
    onPrimary = Paprika20,
    primaryContainer = Paprika30,
    onPrimaryContainer = Paprika90,
    secondary = Clay80,
    secondaryContainer = Clay30,
    onSecondaryContainer = Clay90,
    tertiary = Olive80,
    tertiaryContainer = Olive30,
    background = Espresso,
    surface = Espresso
)

@Composable
fun CookBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Off by default so the app keeps its own warm brand colours.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/** Container and content colours for a difficulty badge. */
fun difficultyColors(difficulty: Difficulty): Pair<Color, Color> = when (difficulty) {
    Difficulty.Easy -> EasyContainer to EasyContent
    Difficulty.Medium -> MediumContainer to MediumContent
    Difficulty.Hard -> HardContainer to HardContent
}
