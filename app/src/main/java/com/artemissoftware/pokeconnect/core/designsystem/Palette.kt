package com.artemissoftware.pokeconnect.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Palette(
    val favorite: Color,
    val notFavorite: Color,
)

internal val paletteLight = Palette(
    favorite = FavoriteRed,
    notFavorite = NotFavoriteRed,
)

internal val paletteDark = Palette(
    favorite = FavoriteRed,
    notFavorite = NotFavoriteRed,
)

data class FixedPalette(
    val shimmer: List<Color>,
    val iconBackground: Color,
)


internal val fixedPalette = FixedPalette(
    shimmer = listOf(GrayShimmer1, GrayShimmer2, GrayShimmer1),
    iconBackground = LightGray,
)

internal val localPalette = staticCompositionLocalOf<Palette> { throw IllegalStateException("No theme installed") }
internal val localFixedPalette = staticCompositionLocalOf<FixedPalette> { throw IllegalStateException("No theme installed") }

val MaterialTheme.palette: Palette
    @Composable
    get() = localPalette.current

val MaterialTheme.fixedPalette: FixedPalette
    @Composable
    get() = localFixedPalette.current

