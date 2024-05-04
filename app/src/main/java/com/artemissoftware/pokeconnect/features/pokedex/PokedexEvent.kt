package com.artemissoftware.pokeconnect.features.pokedex

internal sealed class PokedexEvent {
    data class UpdateSearchQuery(val searchQuery: String) : PokedexEvent()
    data object SearchPokemon : PokedexEvent()
    data class ActivateSearch(val isActive: Boolean) : PokedexEvent()
    data object UpdateFavorite : PokedexEvent()
    data object ClearSearch : PokedexEvent()
}