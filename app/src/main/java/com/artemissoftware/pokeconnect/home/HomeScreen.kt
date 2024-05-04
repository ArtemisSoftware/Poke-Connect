package com.artemissoftware.pokeconnect.home

import androidx.compose.runtime.Composable
import com.artemissoftware.pokeconnect.core.presentation.composables.scaffold.PCScaffold
import com.artemissoftware.pokeconnect.core.ui.navbar.PCNavigationBar
import com.artemissoftware.pokeconnect.features.navigation.HomeNavGraph
import com.artemissoftware.pokeconnect.features.navigation.Route


@Composable
fun HomeScreen(
    appState: PCAppState = rememberPCAppState(),
) {
    PCScaffold(
        content = {
            HomeNavGraph(
                navController = appState.navController,
                startDestination = Route.Pokedex.fullRoute(),
            )
        },
        bottomBar = {
            PCNavigationBar(
                modifier = it,
                destinations = appState.topLevelDestinations,
                currentDestination = appState.currentTopLevelDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
            )
        }
    )
}