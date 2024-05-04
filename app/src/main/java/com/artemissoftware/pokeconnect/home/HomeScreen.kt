package com.artemissoftware.pokeconnect.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.designsystem.window
import com.artemissoftware.pokeconnect.core.presentation.composables.scaffold.PCScaffold
import com.artemissoftware.pokeconnect.core.ui.navbar.PCNavigationBar
import com.artemissoftware.pokeconnect.core.ui.navbar.PCNavigationSideBar
import com.artemissoftware.pokeconnect.core.ui.navbar.TopLevelDestination
import com.artemissoftware.pokeconnect.features.navigation.HomeNavGraph
import com.artemissoftware.pokeconnect.features.navigation.Route

@Composable
fun HomeScreen(
    appState: PCAppState = rememberPCAppState(),
) {
    val isVisible = isVisible(appState.topLevelDestinations, appState.currentTopLevelDestination)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        PCScaffold(
            content = {
                HomeNavGraph(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = if (MaterialTheme.window.isLandScape() && isVisible) MaterialTheme.spacing.spacing10 else 0.dp
                        ),
                    navController = appState.navController,
                    startDestination = Route.Pokedex.fullRoute(),
                )
            },
            bottomBar = {
                if(!MaterialTheme.window.isLandScape()) {
                    PCNavigationBar(
                        isVisible = isVisible,
                        modifier = it,
                        destinations = appState.topLevelDestinations,
                        currentDestination = appState.currentTopLevelDestination,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                    )
                }
            },
        )
    }
    if(MaterialTheme.window.isLandScape()) {
        PCNavigationSideBar(
            isVisible = isVisible,
            destinations = appState.topLevelDestinations,
            currentDestination = appState.currentTopLevelDestination,
            onNavigateToDestination = appState::navigateToTopLevelDestination,
        )
    }
}

private fun isVisible(destinations: List<TopLevelDestination>, currentDestination: TopLevelDestination?): Boolean{
    return destinations.any { it.route == currentDestination?.route }
}

//
//    var selectedItemIndex by rememberSaveable {
//        mutableStateOf(0)
//    }
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        PCScaffold(
//            content = {
//                HomeNavGraph(
//                    navController = appState.navController,
//                    startDestination = Route.Pokedex.fullRoute(),
//                )
//            },
//            bottomBar = {
//                PCNavigationBar(
//                    modifier = it,
//                    destinations = appState.topLevelDestinations,
//                    currentDestination = appState.currentTopLevelDestination,
//                    onNavigateToDestination = appState::navigateToTopLevelDestination,
//                )
//            }
//        )
//
//        if (true) {
//            PCNavigationSideBar(
//                items = appState.topLevelDestinations,
//                selectedItemIndex = selectedItemIndex,
//                onNavigate = { selectedItemIndex = it }
//            )
//        }
//    }
//}