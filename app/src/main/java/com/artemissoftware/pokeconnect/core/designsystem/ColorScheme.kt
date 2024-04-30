package com.artemissoftware.pokeconnect.core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

internal fun Palette.toScheme() = ColorScheme(
    background = background,
    onBackground = onBackground,

    surface = Color.Unspecified,
    onSurface = Color.Unspecified,
    surfaceVariant = Color.Unspecified,
    onSurfaceVariant = Color.Unspecified,

    primary = Color.Unspecified,
    onPrimary = Color.Unspecified,
    primaryContainer = Color.Unspecified,
    onPrimaryContainer = Color.Unspecified,

    secondary = Color.Unspecified,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,

    tertiary = Color.Unspecified,
    onTertiary = Color.Unspecified,
    tertiaryContainer = Color.Unspecified,
    onTertiaryContainer = Color.Unspecified,

    error = Color.Unspecified,
    onError = Color.Unspecified,
    errorContainer = Color.Unspecified,
    onErrorContainer = Color.Unspecified,

    inverseSurface = Color.Unspecified,
    inverseOnSurface = Color.Unspecified,

    outline = Color.Unspecified,
    inversePrimary = Color.Unspecified,
    outlineVariant = Color.Unspecified,
    scrim = Color.Unspecified,
    surfaceTint = Color.Unspecified,
)

@ThemePreviews
@Composable
private fun ColorSchemePreview() {
    PokeConnectTheme {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing2),
            contentPadding = PaddingValues(all = MaterialTheme.spacing.spacing1_5),
        ) {
            item {
                ColorSchemeDescription(
                    text = "background\nonBackground",
                    background = MaterialTheme.colorScheme.background,
                    foreground = MaterialTheme.colorScheme.onBackground,
                )
            }
            item {
                ColorSchemeDescription(
                    text = "secondaryContainer\nonSecondaryContainer",
                    background = MaterialTheme.colorScheme.secondaryContainer,
                    foreground = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

@Composable
private fun ColorSchemeDescription(
    text: String,
    background: Color,
    foreground: Color,
) {
    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.spacing1)
            .height(100.dp)
            .background(
                color = background,
                shape = MaterialTheme.shape.roundedCorners0_5
            ),

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(all = MaterialTheme.spacing.spacing2)
                .align(Alignment.BottomEnd),
            color = foreground,
        )
    }
}
