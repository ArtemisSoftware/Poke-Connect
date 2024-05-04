package com.artemissoftware.pokeconnect.features.detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.common.util.extensions.toFormattedNumber
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.dimension
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
internal fun Display(
    modifier: Modifier = Modifier,
    pokemon: Pokemon? = null,
    paletteColor: PaletteColor = PaletteColor(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            pokemon?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.id.toFormattedNumber(),
                    color = paletteColor.text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            AsyncImage(
                modifier = Modifier
                    .size(MaterialTheme.dimension.imageLarge),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(pokemon?.imageUrl)
                    .error(R.drawable.ic_placeholder)
                    .placeholder(R.drawable.ic_pokeball)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        pokemon?.let {
            TypeSection(
                types = it.types,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@ThemePreviews
@Composable
private fun DisplayPreview() {
    PokeConnectTheme {
        Display(
            modifier = Modifier.fillMaxWidth(),
            pokemon = PreviewData.pokemon,
        )
    }
}