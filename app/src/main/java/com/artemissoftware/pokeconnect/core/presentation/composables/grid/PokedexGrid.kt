package com.artemissoftware.pokeconnect.core.presentation.composables.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.designsystem.window
import com.artemissoftware.pokeconnect.core.models.pokemon.PokedexEntry
import com.artemissoftware.pokeconnect.core.presentation.composables.card.PokedexEntryCard
import com.artemissoftware.pokeconnect.core.presentation.composables.card.ShimmerPokedexEntryCard

@Composable
fun PokedexGrid(
    state: LazyGridState,
    pokedexEntries: LazyPagingItems<PokedexEntry>,
    onClick: (PokedexEntry) -> Unit,
    reloadContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val gridSpan = getGridItemCount()
    val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(gridSpan) }


    LazyVerticalGrid(
        state = state,
        modifier = modifier,
        columns = GridCells.Fixed(getGridItemCount()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
    ) {
        items(
            count = pokedexEntries.itemCount,
            key = pokedexEntries.itemKey { it.id },
            contentType = pokedexEntries.itemContentType { "pokemon" },
        ) {
            pokedexEntries[it]?.let { entry ->
                PokedexEntryCard(
                    pokedexEntry = entry,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onClick(entry) },
                )
            }
        }

        item(span = span) {
            reloadContent()
        }
    }
}

@Composable
fun PokedexGrid(
    pokedexEntries: List<PokedexEntry>,
    onClick: (PokedexEntry) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(getGridItemCount()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
    ) {
        items(pokedexEntries) { entry ->
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
        columns = GridCells.Fixed(getGridItemCount()),
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

@Composable
private fun getGridItemCount(): Int{
    return if(MaterialTheme.window.isLandScape()){
        3
    }
    else{
        2
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