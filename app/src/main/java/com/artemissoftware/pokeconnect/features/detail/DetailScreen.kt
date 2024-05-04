package com.artemissoftware.pokeconnect.features.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.presentation.composables.scaffold.PCScaffold
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor
import com.artemissoftware.pokeconnect.core.ui.panel.Panel
import com.artemissoftware.pokeconnect.core.ui.placeholder.PlaceHolderContent
import com.artemissoftware.pokeconnect.core.ui.util.PaletteUtil
import com.artemissoftware.pokeconnect.features.PreviewData
import com.artemissoftware.pokeconnect.features.detail.composables.DetailTopBar
import com.artemissoftware.pokeconnect.features.detail.composables.Display
import com.artemissoftware.pokeconnect.features.detail.composables.pages.AboutPage
import com.artemissoftware.pokeconnect.features.detail.composables.pages.StatsPage
import com.artemissoftware.pokeconnect.features.detail.models.DetailTab
import com.artemissoftware.pokeconnect.features.detail.models.DetailTab.ABOUT
import com.artemissoftware.pokeconnect.features.detail.models.DetailTab.NOT_FOUND
import com.artemissoftware.pokeconnect.features.detail.models.DetailTab.STATS

@Composable
internal fun DetailScreen(
    onPopBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    DetailScreenContent(
        state = state,
        event = viewModel::onTriggerEvent,
        onPopBack = onPopBack,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DetailScreenContent(
    state: DetailState,
    event: (DetailEvent) -> Unit,
    onPopBack: () -> Unit,
) {
    val pagerState = rememberPagerState {
        state.tabs.size
    }
    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        event(DetailEvent.UpdateSelectedTab(pagerState.currentPage))
    }

    val context = LocalContext.current

    var paletteColor by remember {
        mutableStateOf( PaletteColor())
    }

    LaunchedEffect(key1 = state.pokemon) {
        state.pokemon?.let {
            paletteColor = PaletteUtil.getPaletteFromImageUrl(context = context, imageUrl = it.imageUrl)
        }
    }

    PCScaffold(
        isLoading = state.isLoading,
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Panel(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4F),
                    color = paletteColor.background,
                )
            }

            if (state.pokemon != null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5)
                ) {
                    DetailTopBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.spacing0_5),
                        name = state.pokemon.name,
                        isFavorite = state.pokemon.isFavorite,
                        onBackClick = onPopBack,
                        palette = paletteColor,
                        onFavoriteClick = {
                            event(DetailEvent.UpdateFavorite)
                        },
                    )

                    Display(
                        paletteColor = paletteColor,
                        pokemon = state.pokemon,
                        modifier = Modifier.fillMaxWidth()
                    )

                    ScrollableTabRow(
                        modifier = Modifier.fillMaxWidth(),
                        selectedTabIndex = state.selectedTabIndex,
                        edgePadding = 0.dp
                    ) {
                        state.tabs.forEachIndexed { index, tabItem ->
                            Tab(
                                selected = index == state.selectedTabIndex,
                                onClick = {
                                    event(DetailEvent.UpdateSelectedTab(index))
                                },
                                text = {
                                    val alpha = if (index == state.selectedTabIndex) 1F else 0.3f
                                    Text(
                                        modifier = Modifier.alpha(alpha),
                                        text = stringResource(id = tabItem.title),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                    )
                                },
                            )
                        }
                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { index ->
                        when (DetailTab.getTabByIndex(index)) {
                            ABOUT -> AboutPage(
                                pokemon = state.pokemon,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(all = MaterialTheme.spacing.spacing3),
                            )
                            STATS -> StatsPage(
                                paletteColor = paletteColor,
                                stats = state.pokemon.stats,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(all = MaterialTheme.spacing.spacing3),
                            )
                            NOT_FOUND -> PlaceHolderContent(
                                message = stringResource(id = R.string.page_not_found)
                            )
                        }
                    }
                }
            }
        },
        error = state.error,
    )
}



@ThemePreviews
@Composable
private fun DetailScreenContentPreview() {
    PokeConnectTheme {
        DetailScreenContent(
            state = PreviewData.detailState,
            event = {},
            onPopBack = {},
        )
    }
}