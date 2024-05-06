package com.artemissoftware.pokeconnect.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.artemissoftware.pokeconnect.domain.favorites.usecases.GetFavoritesUseCase
import com.artemissoftware.pokeconnect.domain.favorites.usecases.SearchFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val searchFavoritesUseCase: SearchFavoritesUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow()

    init {
        getFavorites()
    }

    fun onTriggerEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.ActivateSearch -> {
                activateSearch(event.isActive)
            }
            FavoriteEvent.SearchPokemon -> {
                search()
            }
            is FavoriteEvent.UpdateSearchQuery -> {
                updateSearchQuery(searchQuery = event.searchQuery)
            }
            FavoriteEvent.ClearSearch -> clearSearch()
        }
    }

    private fun getFavorites() = with(_state) {
        val pokedex = getFavoritesUseCase()
            .cachedIn(viewModelScope)

        update {
            it.copy(favorites = pokedex)
        }
    }

    private fun search() = with(_state) {
        updateHistory()

        val pokedex = searchFavoritesUseCase(value.searchQuery)
            .cachedIn(viewModelScope)

        update {
            it.copy(searchResult = pokedex)
        }
    }

    private fun updateSearchQuery(searchQuery: String) = with(_state) {
        update {
            it.copy(searchQuery = searchQuery)
        }
    }

    private fun updateHistory() = with(_state) {
        val history = value.searchHistory.toMutableList()

        if(!history.contains(value.searchQuery)) {
            history.add(0, value.searchQuery)
            update {
                it.copy(searchHistory = history, isSearching = false)
            }
        }
        else{
            update {
                it.copy(isSearching = false)
            }
        }
    }

    private fun activateSearch(isActive: Boolean) = with(_state) {
        update {
            it.copy(isSearching = isActive)
        }
    }

    private fun clearSearch() = with(_state) {
        update {
            it.copy(isSearching = false, searchQuery = "")
        }
    }
}