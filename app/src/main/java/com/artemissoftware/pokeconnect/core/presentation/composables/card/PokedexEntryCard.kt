package com.artemissoftware.pokeconnect.core.presentation.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.ui.util.extensions.shimmerEffect
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
fun PokedexEntryCard(
    pokedexEntry: PokedexEntry,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = backGroundColor),
        onClick = { onClick() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.spacing2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5)
        ) {

            AsyncImage(
                modifier = Modifier
                    .size(MaterialTheme.dimension.cardImage),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(pokedexEntry.imageUrl)
                    .error(R.drawable.ic_placeholder)
                    .placeholder(R.drawable.ic_pokeball)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Text(
                text = pokedexEntry.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                text = pokedexEntry.id.toFormattedNumber(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun ShimmerPokedexEntryCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondaryContainer),
    ) {
        Column(
            modifier = modifier
                .padding(MaterialTheme.spacing.spacing2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                modifier = Modifier
                    .size(MaterialTheme.dimension.cardImage),
                contentDescription = "",
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(MaterialTheme.dimension.shimmerTextHeight)
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(MaterialTheme.dimension.shimmerTextHeight)
                    .shimmerEffect()
            )
        }
    }
}


@ThemePreviews
@Composable
private fun PokedexEntryCardPreview() {
    PokeConnectTheme {
        PokedexEntryCard(
            pokedexEntry = PreviewData.pokedexEntries[0],
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}

@ThemePreviews
@Composable
private fun ShimmerPokedexEntryCardPreview() {
    PokeConnectTheme {
        ShimmerPokedexEntryCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
