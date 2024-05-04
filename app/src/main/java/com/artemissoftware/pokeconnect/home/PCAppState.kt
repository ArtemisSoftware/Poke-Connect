package com.artemissoftware.pokeconnect.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.artemissoftware.pokeconnect.core.ui.navbar.TopLevelDestination
import com.artemissoftware.pokeconnect.features.navigation.navigateToFavorites
import com.artemissoftware.pokeconnect.features.navigation.navigateToPokedex
import com.artemissoftware.pokeconnect.home.BottomBarDestinations.destinations
import com.artemissoftware.pokeconnect.home.BottomBarDestinations.favorites
import com.artemissoftware.pokeconnect.home.BottomBarDestinations.pokedex

class PCAppState (
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            pokedex.route -> pokedex
            favorites.route -> favorites
            else -> null
        }

//    /**
//     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
//     * route.
//     */
    val topLevelDestinations: List<TopLevelDestination> = destinations

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.route}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination.route) {
                pokedex.route -> navController.navigateToPokedex(topLevelNavOptions)
                favorites.route -> navController.navigateToFavorites(topLevelNavOptions)
            }
        }
    }
}

@Composable
fun rememberPCAppState(
    navController: NavHostController = rememberNavController(),
): PCAppState {
    return remember(
        navController,
    ) {
        PCAppState(
            navController,
        )
    }
}