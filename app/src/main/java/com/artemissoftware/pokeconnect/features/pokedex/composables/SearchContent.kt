package com.artemissoftware.pokeconnect.features.pokedex.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.common.util.extensions.toFormattedNumber
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.dimension
import com.artemissoftware.pokeconnect.core.designsystem.palette
import com.artemissoftware.pokeconnect.core.designsystem.shape
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.designsystem.window.WindowContent
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.ui.icon.CircularIcon
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor
import com.artemissoftware.pokeconnect.core.ui.util.PaletteUtil
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
internal fun SearchContent(
    isLoading: Boolean,
    onClick: (Int) -> Unit,
    onFavorite: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
    pokemon: Pokemon? = null,
) {
    val context = LocalContext.current

    var paletteColor by remember {
        mutableStateOf( PaletteColor())
    }

    val isDarkMode = isSystemInDarkTheme()

    LaunchedEffect(key1 = pokemon, key2 = isDarkMode) {
        pokemon?.let {
            paletteColor = PaletteUtil.getPaletteFromImageUrl(context = context, imageUrl = it.imageUrl, isDarkMode = isDarkMode)
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        WindowContent(
            landScapeContent = {
                LandScapeContent(
                    paletteColor = paletteColor,
                    onFavorite = onFavorite,
                    pokemon = pokemon,
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .clip(MaterialTheme.shape.roundedCorners2)
                        .clickable {
                            pokemon?.let { onClick(it.id) }
                        }
                        .background(color = paletteColor.background)
                        .padding(all = MaterialTheme.spacing.spacing2),
                )
            },
            portraitContent = {
                PortraitContent(
                    paletteColor = paletteColor,
                    onFavorite = onFavorite,
                    pokemon = pokemon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shape.roundedCorners2)
                        .clickable {
                            pokemon?.let { onClick(it.id) }
                        }
                        .background(color = paletteColor.background)
                        .padding(all = MaterialTheme.spacing.spacing1),
                )
            },
        )
    }
}

@Composable
private fun PortraitContent(
    paletteColor: PaletteColor,
    onFavorite: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
    pokemon: Pokemon? = null,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {

        pokemon?.let {
            CircularIcon(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                icon = R.drawable.ic_love,
                tint = if (it.isFavorite) MaterialTheme.palette.favorite else MaterialTheme.palette.notFavorite,
                onClick = {
                    onFavorite(it)
                },
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(vertical = MaterialTheme.spacing.spacing0_5),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(MaterialTheme.dimension.imageMedium),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(pokemon?.imageUrl)
                    .size(Size.ORIGINAL)
                    .error(R.drawable.ic_placeholder)
                    .placeholder(R.drawable.ic_pokeball)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )

            Detail(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.spacing1),
                pokemon = pokemon,
                textColor = paletteColor.text,
            )
        }
    }
}


@Composable
private fun LandScapeContent(
    paletteColor: PaletteColor,
    onFavorite: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
    pokemon: Pokemon? = null,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(MaterialTheme.dimension.imageMedium),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(pokemon?.imageUrl)
                .size(Size.ORIGINAL)
                .error(R.drawable.ic_placeholder)
                .placeholder(R.drawable.ic_pokeball)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        Detail(
            modifier = Modifier
                .weight(0.8F),
            pokemon = pokemon,
            textColor = paletteColor.text,
        )

        pokemon?.let {
            CircularIcon(
                modifier = Modifier,
                icon = R.drawable.ic_love,
                tint = if (it.isFavorite) MaterialTheme.palette.favorite else MaterialTheme.palette.notFavorite,
                onClick = {
                    onFavorite(it)
                },
            )
        }
    }
}


@Composable
private fun Detail(
    textColor: Color,
    modifier: Modifier = Modifier,
    pokemon: Pokemon? = null,
) {
    pokemon?.let {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = it.name.upperCaseFirstChar(),
                style = MaterialTheme.typography.titleLarge,
                color = textColor,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = it.id.toFormattedNumber(),
                color = textColor,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@ThemePreviews
@Composable
private fun SearchContentPreview() {
    PokeConnectTheme {
        SearchContent(
            isLoading = false,
            onClick = {},
            onFavorite = {},
            modifier = Modifier.fillMaxWidth(),
            pokemon = PreviewData.pokemon,
        )
    }
}