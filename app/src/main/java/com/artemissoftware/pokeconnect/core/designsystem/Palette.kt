package com.artemissoftware.pokeconnect.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Palette(
    val background: Color,
    val onBackground: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
)

internal val paletteLight = Palette(
    background = White,
    onBackground = Black,
    onSecondary = WhiteGray,
    secondaryContainer = LightGray,
    onSecondaryContainer = DarkBlue
)

internal val paletteDark = Palette(
    background = Black,
    onBackground = White,
    onSecondary = WhiteGray,
    secondaryContainer = DarkBlue,
    onSecondaryContainer = White
)

data class FixedPalette(
    val shimmer: List<Color>,
)

internal val fixedPalette = FixedPalette(
    shimmer = listOf(GrayShimmer1, GrayShimmer2, GrayShimmer1),
)

internal val localPalette = staticCompositionLocalOf<Palette> { throw IllegalStateException("No theme installed") }
internal val localFixedPalette = staticCompositionLocalOf<FixedPalette> { throw IllegalStateException("No theme installed") }

val MaterialTheme.palette: Palette
    @Composable
    get() = localPalette.current

val MaterialTheme.fixedPalette: FixedPalette
    @Composable
    get() = localFixedPalette.current

