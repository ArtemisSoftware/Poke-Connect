package com.artemissoftware.pokeconnect.features.pokedex

import androidx.paging.PagingData
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import kotlinx.coroutines.flow.Flow

internal data class PokedexState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchHistory: List<String> = emptyList(),
    val searchResult: Pokemon? = null,
    val pokedex: Flow<PagingData<PokedexEntry>>? = null,
    val error: ErrorData? = null,
)
