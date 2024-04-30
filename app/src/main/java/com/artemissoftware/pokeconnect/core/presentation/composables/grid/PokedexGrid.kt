package com.artemissoftware.pokeconnect.core.presentation.composables.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.presentation.composables.card.PokedexEntryCard
import com.artemissoftware.pokeconnect.core.presentation.composables.card.ShimmerPokedexEntryCard
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
fun PokedexGrid(
    pokedexEntries: List<PokedexEntry>,
    onClick: (PokedexEntry) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
    ) {
        items(pokedexEntries){ entry ->
            PokedexEntryCard(
                pokedexEntry = entry,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick(entry) },
            )
        }
    }
}

@Composable
fun ShimmerPokedexGrid(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
    ) {
        items(10){
            ShimmerPokedexEntryCard(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PokedexGridPreview() {
    PokeConnectTheme {
        PokedexGrid(
            pokedexEntries = PreviewData.pokedexEntries,
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}

@ThemePreviews
@Composable
private fun ShimmerPokedexGridPreview() {
    PokeConnectTheme {
        ShimmerPokedexGrid(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}