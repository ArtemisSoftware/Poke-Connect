package com.artemissoftware.pokeconnect.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import com.artemissoftware.pokeconnect.core.ui.navbar.TopLevelDestination
import com.artemissoftware.pokeconnect.features.navigation.Route

object BottomBarDestinations {

    val pokedex = TopLevelDestination(
        route = Route.Pokedex.fullRoute(),
        title = "Random",
        icon = Icons.Default.PlayArrow,
    )

    val favorites = TopLevelDestination(
        route = Route.Favorites.fullRoute(),
        title = "Food",
        icon = Icons.Default.Favorite,
    )

    val destinations = listOf(pokedex, favorites)
}