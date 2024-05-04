package com.artemissoftware.pokeconnect.features.favorites

import androidx.paging.PagingData
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import kotlinx.coroutines.flow.Flow

internal data class FavoritesState(
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchHistory: List<String> = emptyList(),
    val searchResult: Flow<PagingData<PokedexEntry>>? = null,
    val favorites: Flow<PagingData<PokedexEntry>>? = null,
){

    fun pokedexResult() = if (searchQuery.isNotEmpty()) {
        searchResult
    } else favorites

}
