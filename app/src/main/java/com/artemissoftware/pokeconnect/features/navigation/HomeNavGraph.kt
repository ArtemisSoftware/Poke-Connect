package com.artemissoftware.pokeconnect.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artemissoftware.pokeconnect.features.detail.DetailScreen
import com.artemissoftware.pokeconnect.features.favorites.FavoritesScreen
import com.artemissoftware.pokeconnect.features.pokedex.PokedexScreen

const val HOME_GRAPH = "home_graph"

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        startDestination = startDestination,
        route = HOME_GRAPH,
        navController = navController,
    ) {

        composable(route = Route.Pokedex.fullRoute()) {
            PokedexScreen(
                navigateToDetails = {
                    navController.navigateToDetail(it)
                },
                navigateToFavorites = {
                    navController.navigate(Route.Favorites.fullRoute())
                },
            )
        }

        composable(route = Route.Favorites.fullRoute()) {
            FavoritesScreen(
                onPopBack = {
                    navController.popBackStack()
                },
                navigateToDetails = {
                    navController.navigateToDetail(it)
                },
            )
        }

        composable(
            route = Route.Detail.fullRoute(),
            arguments = Route.Detail.arguments,
        ) {
            DetailScreen(
                onPopBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}

fun NavController.navigateToPokedex(navOptions: NavOptions) = navigate(Route.Pokedex.fullRoute(), navOptions)
fun NavController.navigateToFavorites(navOptions: NavOptions) = navigate(Route.Favorites.fullRoute(), navOptions)

fun NavHostController.navigateToDetail(query: String) = this.navigate(Route.Detail.withCustomArgs(query))

sealed class Route(
    route: String,
    arguments: List<NamedNavArgument> = emptyList()
): BaseDestination(
    route = route,
    arguments = arguments,
) {
    data object Pokedex : Route(route = "pokedex")

    data object Favorites : Route(route = "favorites")

    data object Detail : Route(
        route = "detail",
        arguments = listOf(
            navArgument(
                name = NavArguments.ID,
            ) {
                type = NavType.StringType
            },
        ),
    )
}