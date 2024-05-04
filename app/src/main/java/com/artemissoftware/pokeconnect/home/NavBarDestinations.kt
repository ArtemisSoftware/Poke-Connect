package com.artemissoftware.pokeconnect.home

import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.ui.navbar.TopLevelDestination
import com.artemissoftware.pokeconnect.features.navigation.Route

object NavBarDestinations {

    val pokedex = TopLevelDestination(
        route = Route.Pokedex.fullRoute(),
        title = R.string.pokedex,
        icon = R.drawable.ic_pokedex,
    )

    val favorites = TopLevelDestination(
        route = Route.Favorites.fullRoute(),
        title = R.string.favorites,
        icon = R.drawable.ic_favorite,
    )

    val destinations = listOf(pokedex, favorites)
}