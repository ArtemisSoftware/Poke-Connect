package com.artemissoftware.pokeconnect.features.detail.composables

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.palette
import com.artemissoftware.pokeconnect.core.ui.icon.CircularIcon
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailTopBar(
    name: String,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    palette: PaletteColor = PaletteColor(),
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            CircularIcon(
                icon = R.drawable.ic_back,
                onClick = onBackClick,
            )
        },
        title = {
            Text(
                text = name.upperCaseFirstChar(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = palette.text,
            )
        },
        actions = {
            CircularIcon(
                icon = R.drawable.ic_love,
                tint = if(isFavorite) MaterialTheme.palette.favorite else MaterialTheme.palette.notFavorite,
                onClick = { onFavoriteClick() },
            )
        }
    )
}

@ThemePreviews
@Composable
private fun DetailTopBarPreview() {
    PokeConnectTheme {
        DetailTopBar(
            name = "bulbasaur",
            isFavorite = true,
            onBackClick = {},
            onFavoriteClick = {},
            palette = PaletteColor(),
        )
    }
}

@ThemePreviews
@Composable
private fun DetailTopBarNotFavoritePreview() {
    PokeConnectTheme {
        DetailTopBar(
            name = "bulbasaur",
            isFavorite = false,
            onBackClick = {},
            onFavoriteClick = {},
            palette = PaletteColor(),
        )
    }
}