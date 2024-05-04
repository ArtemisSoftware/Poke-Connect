package com.artemissoftware.pokeconnect.features.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
internal fun FavoritesScreen(
    onPopBack: () -> Unit,
    navigateToDetails: (String) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    FavoritesScreenContent(
        state = state,
        event = viewModel::onTriggerEvent,
        navigateToDetails = navigateToDetails,
        onPopBack = onPopBack,
    )
}

@Composable
private fun FavoritesScreenContent(
    state: FavoritesState,
    event: (FavoriteEvent) -> Unit,
    navigateToDetails: (String) -> Unit,
    onPopBack: () -> Unit,
) {
    val gridState = rememberLazyGridState()
    val searchBarSize = animateDpAsState(
        targetValue = if (state.isSearching) 0.dp else MaterialTheme.spacing.spacing3,
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
                                onPopBack = onPopBack
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
                    placeHolderText = stringResource(id = if(MaterialTheme.window.isLandScape()) R.string.name_number_favorites else R.string.name_number),
                    isSearching = state.isSearching,
                    onQueryChange = {
                        event(FavoriteEvent.UpdateSearchQuery(searchQuery = it))
                    },
                    onSearch = {
                        event(FavoriteEvent.SearchPokemon)
                    },
                    onActiveChange = {
                        event(FavoriteEvent.ActivateSearch(isActive = it))
                    },
                    onClearSearch = {
                        event(FavoriteEvent.ClearSearch)
                    }
                )

                AnimatedVisibility(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.spacing1_5),
                    visible = !state.isSearching,
                    exit = fadeOut()
                ) {
                    state.pokedexResult()?.let {

                        val items = it.collectAsLazyPagingItems()

                        if(items.itemCount == 0){
                            PlaceHolderContent(
                                message = stringResource(id = R.string.no_favorites_found),
                                icon = R.drawable.ic_placeholder
                            )
                        }
                        else {
                            PaginationContent(
                                items = items,
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
                                        pokedexEntries = pokedexEntries,
                                        onClick = { entry ->
                                            navigateToDetails(entry.id.toString())
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = MaterialTheme.spacing.spacing3),
                                        state = gridState,
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun Header(
    onPopBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(0.55F),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5)
        ) {
            Text(
                text = stringResource(id = R.string.favorites),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(id = R.string.here_are_your_favorites),
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Icon(
            modifier = Modifier.clickable {
                onPopBack()
            },
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
        )
    }
}

@ThemePreviews
@Composable
private fun FavoritesScreenContentPreview() {
    PokeConnectTheme {
        FavoritesScreenContent(
            state = PreviewData.favoriteState,
            event = {},
            navigateToDetails = {},
            onPopBack = {},
        )
    }
}