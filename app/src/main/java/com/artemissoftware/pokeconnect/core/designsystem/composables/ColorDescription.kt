package com.artemissoftware.pokeconnect.core.designsystem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.artemissoftware.pokeconnect.core.designsystem.shape
import com.artemissoftware.pokeconnect.core.designsystem.spacing

@Composable
internal fun ColorDescription(
    text: String,
    background: Color,
    foreground: Color = MaterialTheme.colorScheme.surface,
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