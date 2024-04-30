package com.artemissoftware.pokeconnect.core.designsystem

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shaping(
    val circular: Shape,
    val roundedCorners0_5: Shape,
    val roundedCorners2: Shape,
)

internal val shape = Shaping(
    circular = CircleShape,
    roundedCorners0_5 = RoundedCornerShape(4.dp),
    roundedCorners2 = RoundedCornerShape(16.dp),
)

internal val localShape = staticCompositionLocalOf<Shaping> { throw IllegalStateException("No theme installed") }

val MaterialTheme.shape: Shaping
    @Composable
    get() = localShape.current
