package com.artemissoftware.pokeconnect.core.designsystem

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.artemissoftware.pokeconnect.core.designsystem.palette.pokemon.localPokemonPalette
import com.artemissoftware.pokeconnect.core.designsystem.palette.pokemon.pokemonPalette
import com.artemissoftware.pokeconnect.core.designsystem.window.WindowSize
import com.artemissoftware.pokeconnect.core.designsystem.window.rememberWindowSize

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PokeConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    windowSize: WindowSize = rememberWindowSize(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    CompositionLocalProvider(
        localWindow provides if(windowSize.isLandScape()) landScape else portrait,
        localSpacing provides spacing,
        localDimension provides if(windowSize.isLandScape()) dimensionLandScape else dimensionPortrait,
        localShape provides shape,
        localPalette provides if (darkTheme) paletteDark else paletteLight,
        localFixedPalette provides fixedPalette,
        localPokemonPalette provides pokemonPalette,
    ) {
        //val colorScheme = localPalette.current.toScheme()

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}