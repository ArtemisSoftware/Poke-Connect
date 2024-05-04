package com.artemissoftware.pokeconnect.core.ui.panel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.palette.pokemon.pokemonPalette

@Composable
fun Panel(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
    ) {
        drawPath(
            path = createOvalBottomPath(),
            color = color,
        )
    }
}

private fun DrawScope.createOvalBottomPath(): Path {
    val path = Path()
    path.moveTo(0f, 0f) // Top-left corner
    path.lineTo(size.width, 0f) // Top-right corner
    path.lineTo(size.width, (size.height /(1.2)).toFloat() ) // Bottom-right corner (before oval)
    path.quadraticBezierTo(size.width / 2, size.height  , 0f, (size.height /(1.2)).toFloat())
    path.close()
    return path
}

@ThemePreviews
@Composable
private fun DisplayPreview() {
    PokeConnectTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Panel(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45F),
                color = MaterialTheme.pokemonPalette.fire,
            )
        }

    }
}