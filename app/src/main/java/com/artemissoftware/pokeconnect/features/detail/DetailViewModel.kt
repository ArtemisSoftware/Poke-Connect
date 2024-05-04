package com.artemissoftware.pokeconnect.features.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.domain.usecases.SearchPokemonUseCase
import com.artemissoftware.pokeconnect.core.domain.usecases.UpdateFavoriteUseCase
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import com.artemissoftware.pokeconnect.core.presentation.util.extensions.toUiText
import com.artemissoftware.pokeconnect.core.ui.text.UiText
import com.artemissoftware.pokeconnect.features.navigation.NavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val searchPokemonUseCase: SearchPokemonUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(getState() ?:  DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    private var pokemonId = ""

    init {
        savedStateHandle.get<String>(NavArguments.ID)?.let {
            pokemonId = it
            getPokemon(it)
        }
    }

    private fun updateState(update: (DetailState) -> DetailState) {
        savedStateHandle["state"] = _state.updateAndGet { update(it) }
    }

    private fun getState() = (savedStateHandle.get<DetailState>("state"))?.copy(isLoading = false, selectedTabIndex = 0)

    fun onTriggerEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.UpdateFavorite -> updateFavorite()
            is DetailEvent.UpdateSelectedTab -> updateSelectedTab(index = event.index)
        }
    }

    private fun getPokemon(id: String) = with(_state) {

        updateLoading(true)

        viewModelScope.launch {
            searchPokemonUseCase(query = id)
                .onSuccess { pokemon ->
                    updateState {
                        it.copy(pokemon = pokemon.first(), isLoading = false, error = null)
                    }
                }
                .onFailure { error ->
                    updateState {
                        it.copy(
                            pokemon = null,
                            isLoading = false,
                            error = ErrorData(
                                message = error.toUiText(),
                                buttonText = UiText.StringResource(R.string.try_again),
                                onClick = {
                                    reload(pokemonId)
                                }
                            ),
                        )
                    }
                }
        }
    }

    private fun reload(pokemonId: String){ getPokemon(pokemonId) }

    private fun updateFavorite() = with(_state) {
        viewModelScope.launch {
            value.pokemon?.let { result ->
                updateFavoriteUseCase(result)

                val pokemon = result.copy(isFavorite = !result.isFavorite)
                updateState {
                    it.copy(pokemon = pokemon)
                }
            }
        }
    }


    private fun updateSelectedTab(index: Int) = with(_state) {
        updateState {
            it.copy(selectedTabIndex = index)
        }
    }

    private fun updateLoading(loading: Boolean) = with(_state) {
        updateState {
            it.copy(isLoading = loading)
        }
    }
}