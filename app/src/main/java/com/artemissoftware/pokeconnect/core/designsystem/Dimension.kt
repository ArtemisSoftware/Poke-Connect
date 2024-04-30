package com.artemissoftware.pokeconnect.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class Dimension(
    val cardImage: DpSize,
    val iconChipSize: DpSize,
    val iconSize: DpSize,
    val iconSizeBig: DpSize,
    val iconContentSize: DpSize,
    val statMaxWidth: Dp,
    val shimmerTextHeight: Dp,
)

val dimension = Dimension(
    cardImage = DpSize(width = 120.dp, height = 120.dp),
    iconSize = DpSize(width = 46.dp, height = 46.dp),
    iconSizeBig = DpSize(width = 120.dp, height = 120.dp),
    iconChipSize = DpSize(width = 18.dp, height = 18.dp),
    iconContentSize = DpSize(width = 20.dp, height = 20.dp),
    statMaxWidth = 48.dp,
    shimmerTextHeight = 20.dp
)

internal val localDimension = staticCompositionLocalOf<Dimension> { throw IllegalStateException("No theme installed") }

val MaterialTheme.dimension: Dimension
    @Composable
    get() = localDimension.current
