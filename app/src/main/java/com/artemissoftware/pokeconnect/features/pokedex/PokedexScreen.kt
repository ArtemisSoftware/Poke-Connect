package com.artemissoftware.pokeconnect.features.pokedex

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.composables.search.PCSearchBar
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.designsystem.window
import com.artemissoftware.pokeconnect.core.designsystem.window.WindowContent
import com.artemissoftware.pokeconnect.core.presentation.composables.grid.PokedexGrid
import com.artemissoftware.pokeconnect.core.presentation.composables.grid.ShimmerPokedexGrid
import com.artemissoftware.pokeconnect.core.presentation.composables.pagination.PaginationContent
import com.artemissoftware.pokeconnect.core.presentation.composables.scaffold.PCScaffold
import com.artemissoftware.pokeconnect.core.ui.placeholder.PlaceHolderContent
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
internal fun PokedexScreen(
    navigateToDetails: (String) -> Unit,
    viewModel: PokedexViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    PokedexScreenContent(
        state = state,
        event = viewModel::onTriggerEvent,
        navigateToDetails = navigateToDetails,
    )
}

@Composable
private fun PokedexScreenContent(
    state: PokedexState,
    event: (PokedexEvent) -> Unit,
    navigateToDetails: (String) -> Unit,
) {

    val gridState = rememberLazyGridState()
    val searchBarSize = animateDpAsState(
        targetValue = if (state.isSearching || MaterialTheme.window.isLandScape()) 0.dp else MaterialTheme.spacing.spacing3,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "",
    )

    PCScaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = searchBarSize.value)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5)
            ) {

                WindowContent(
                    landScapeContent = {},
                    portraitContent = {
                        AnimatedVisibility(
                            visible = !state.isSearching,
                            exit = slideOutHorizontally(
                                targetOffsetX = { -it },
                                animationSpec = tween(durationMillis = 300)
                            ) + shrinkVertically(
                                animationSpec = tween(delayMillis = 300)
                            )
                        ) {
                            Header(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = MaterialTheme.spacing.spacing3),
                            )
                        }
                    }
                )

                PCSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = searchBarSize.value),
                    historyItems = state.searchHistory,
                    text = state.searchQuery,
                    placeHolderText = stringResource(id = R.string.name_number),
                    isSearching = state.isSearching,
                    onQueryChange = {
                        event(PokedexEvent.UpdateSearchQuery(searchQuery = it))
                    },
                    onSearch = {
                        event(PokedexEvent.SearchPokemon)
                    },
                    onActiveChange = {
                        event(PokedexEvent.ActivateSearch(isActive = it))
                    },
                    onClearSearch = {
                        event(PokedexEvent.ClearSearch)
                    }
                )

                AnimatedVisibility(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.spacing1_5),
                    visible = !state.isSearching && state.error == null,
                    exit = fadeOut()
                ) {
                    if (state.searchQuery.isNotEmpty()) {

                        PokedexGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.spacing.spacing3),
                            pokedexEntries = state.searchPokedexResult(),
                            onClick = { entry ->
                                navigateToDetails(entry.id.toString())
                            },
                        )
                    } else {
                        state.pokedex?.let {
                            PaginationContent(
                                items = it.collectAsLazyPagingItems(),
                                loadingContent = {
                                    ShimmerPokedexGrid(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = MaterialTheme.spacing.spacing3)
                                    )
                                },
                                errorContent = { error ->
                                    PlaceHolderContent(message = error.asString())
                                },
                                content = { pokedexEntries ->
                                    PokedexGrid(
                                        state = gridState,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = MaterialTheme.spacing.spacing3),
                                        pokedexEntries = pokedexEntries,
                                        onClick = { entry ->
                                            navigateToDetails(entry.id.toString())
                                        },
                                    )
                                }
                            )
                        }
                    }
                }
            }
        },
        isLoading = state.isLoading,
        error = state.error,
    )
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5)
    ) {
        Text(
            text = stringResource(id = R.string.pokedex),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(id = R.string.search_pokemon_by_name_number),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@ThemePreviews
@Composable
private fun PokedexScreenContentPreview() {
    PokeConnectTheme {
        PokedexScreenContent(
            state = PreviewData.pokedexState,
            event = {},
            navigateToDetails = {},
        )
    }
}

@ThemePreviews
@Composable
private fun PokedexScreenContent_SearchComplete_Preview() {
    PokeConnectTheme {
        PokedexScreenContent(
            state = PreviewData.pokedexState_search_complete,
            event = {},
            navigateToDetails = {},
        )
    }
}

@ThemePreviews
@Composable
private fun PokedexScreenContent_Searching_Preview() {
    PokeConnectTheme {
        PokedexScreenContent(
            state = PreviewData.pokedexState_searching,
            event = {},
            navigateToDetails = {},
        )
    }
}