package com.artemissoftware.pokeconnect.features.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.domain.pokedex.usecases.GetPokedexUseCase
import com.artemissoftware.pokeconnect.domain.pokedex.usecases.SearchPokemonUseCase
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import com.artemissoftware.pokeconnect.core.presentation.util.extensions.toUiText
import com.artemissoftware.pokeconnect.core.ui.text.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PokedexViewModel @Inject constructor(
    private val getPokedexUseCase: GetPokedexUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(PokedexState())
    val state: StateFlow<PokedexState> = _state.asStateFlow()

    init {
        getPokedex()
    }

    fun onTriggerEvent(event: PokedexEvent) {
        when (event) {
            is PokedexEvent.ActivateSearch -> {
                activateSearch(event.isActive)
            }
            PokedexEvent.SearchPokemon -> {
                search()
            }
            is PokedexEvent.UpdateSearchQuery -> {
                updateSearchQuery(searchQuery = event.searchQuery)
            }
            PokedexEvent.ClearSearch -> clearSearch()
            PokedexEvent.Reload -> getPokedex()
        }
    }

    private fun getPokedex() = with(_state) {
        val pokedex = getPokedexUseCase()
            .cachedIn(viewModelScope)

        update {
            it.copy(pokedex = pokedex)
        }
    }

    private fun search() = with(_state) {
        updateHistory()
        updateStartSearch()

        viewModelScope.launch {
            searchPokemonUseCase(query = value.searchQuery)
                .onSuccess { pokemon ->
                    update {
                        it.copy(searchResult = pokemon, isLoading = false, error = null)
                    }
                }
                .onFailure { error ->
                    update {
                        it.copy(
                            searchResult = emptyList(),
                            isLoading = false,
                            error = ErrorData(
                                message = error.toUiText(),
                                buttonText = UiText.StringResource(R.string.try_again),
                                onClick = {
                                    reloadSearch()
                                }
                            ),
                        )
                    }
                }
        }
    }

    private fun reloadSearch(){ search() }


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
            it.copy(isSearching = false, searchQuery = "", error = null)
        }
    }

    private fun updateStartSearch() = with(_state) {
        update {
            it.copy(isLoading = true, error = null)
        }
    }
}