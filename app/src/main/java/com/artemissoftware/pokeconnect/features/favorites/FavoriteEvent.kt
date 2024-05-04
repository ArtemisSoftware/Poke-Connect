package com.artemissoftware.pokeconnect.features.favorites

internal sealed class FavoriteEvent {
    data class UpdateSearchQuery(val searchQuery: String) : FavoriteEvent()
    data object SearchPokemon : FavoriteEvent()
    data class ActivateSearch(val isActive: Boolean) : FavoriteEvent()
    data object ClearSearch : FavoriteEvent()
}