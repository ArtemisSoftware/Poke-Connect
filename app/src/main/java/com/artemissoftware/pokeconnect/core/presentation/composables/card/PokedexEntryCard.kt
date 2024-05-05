package com.artemissoftware.pokeconnect.core.presentation.composables.card

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.common.util.extensions.toFormattedNumber
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.dimension
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.designsystem.window.WindowContent
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_CARD
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_CARD_CONTENT
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_ID
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_IMAGE
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_NAME
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_SHIMMER_CARD
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_SHIMMER_ID
import com.artemissoftware.pokeconnect.core.presentation.TestTags.POKEDEX_ENTRY_SHIMMER_NAME
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor
import com.artemissoftware.pokeconnect.core.ui.util.PaletteUtil
import com.artemissoftware.pokeconnect.core.ui.util.extensions.shimmerEffect
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
fun PokedexEntryCard(
    pokedexEntry: PokedexEntry,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    var paletteColor by remember {
        mutableStateOf( PaletteColor())
    }

    val isDarkMode = isSystemInDarkTheme()

    LaunchedEffect(key1 = pokedexEntry, key2 = isDarkMode) {
        paletteColor = PaletteUtil.getPaletteFromImageUrl(context = context, imageUrl = pokedexEntry.imageUrl, isDarkMode = isDarkMode)
    }

    Card(
        modifier = modifier
            .testTag(POKEDEX_ENTRY_CARD),
        colors = CardDefaults.cardColors(containerColor = paletteColor.background),
        onClick = { onClick() },
    ) {

        WindowContent(
            portraitContent = {
                PortraitContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                    paletteColor = paletteColor,
                    pokedexEntry = pokedexEntry,
                )
            },
            landScapeContent = {
                LandscapeContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                    paletteColor = paletteColor,
                    pokedexEntry = pokedexEntry,
                )
            }
        )
    }
}

@Composable
fun ShimmerPokedexEntryCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .testTag(POKEDEX_ENTRY_SHIMMER_CARD),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {

        WindowContent(
            portraitContent = {
                PortraitContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                )
            },
            landScapeContent = {
                LandscapeContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                )
            }
        )
    }
}

@Composable
private fun PortraitContent (
    modifier: Modifier = Modifier,
    pokedexEntry: PokedexEntry? = null,
    paletteColor: PaletteColor = PaletteColor(),
) {
    var request = ImageRequest
        .Builder(LocalContext.current)
        .placeholder(R.drawable.ic_pokeball)
        .crossfade(true)

    pokedexEntry?.let {
        request = request
            .data(it.imageUrl)
            .error(R.drawable.ic_placeholder)
    }

    Column(
        modifier = modifier
            .testTag(POKEDEX_ENTRY_CARD_CONTENT),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1)
    ) {

        AsyncImage(
            modifier = Modifier
                .testTag(POKEDEX_ENTRY_IMAGE)
                .size(MaterialTheme.dimension.cardImage),
            model = request
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Description(
            modifier = Modifier
                .fillMaxWidth(),
            paletteColor = paletteColor,
            pokedexEntry = pokedexEntry,
        )
    }
}

@Composable
private fun LandscapeContent (
    modifier: Modifier = Modifier,
    pokedexEntry: PokedexEntry? = null,
    paletteColor: PaletteColor = PaletteColor(),
) {
    var request = ImageRequest
        .Builder(LocalContext.current)
        .placeholder(R.drawable.ic_pokeball)
        .crossfade(true)

    pokedexEntry?.let {
        request = request
            .data(it.imageUrl)
            .error(R.drawable.ic_placeholder)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .size(MaterialTheme.dimension.cardImage),
            model = request
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Description(
            modifier = Modifier
                .fillMaxWidth(),
            paletteColor = paletteColor,
            pokedexEntry = pokedexEntry,
        )

    }
}

@Composable
private fun Description(
    modifier: Modifier = Modifier,
    pokedexEntry: PokedexEntry? = null,
    paletteColor: PaletteColor = PaletteColor(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        pokedexEntry?.let {
            Text(
                text = it.name.upperCaseFirstChar(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = paletteColor.text,
                modifier = Modifier
                    .testTag(POKEDEX_ENTRY_NAME)
                    .fillMaxWidth(),
            )

            Text(
                text = it.id.toFormattedNumber(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = paletteColor.text,
                modifier = Modifier
                    .testTag(POKEDEX_ENTRY_ID)
                    .fillMaxWidth(),
            )
        } ?: run{
            Box(
                modifier = Modifier
                    .testTag(POKEDEX_ENTRY_SHIMMER_NAME)
                    .fillMaxWidth(0.7f)
                    .height(MaterialTheme.dimension.shimmerTextHeight)
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .testTag(POKEDEX_ENTRY_SHIMMER_ID)
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
