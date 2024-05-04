package com.artemissoftware.pokeconnect.features.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.domain.usecases.GetPokemonUseCase
import com.artemissoftware.pokeconnect.core.domain.usecases.UpdateFavoriteUseCase
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import com.artemissoftware.pokeconnect.core.presentation.util.extensions.toUiText
import com.artemissoftware.pokeconnect.core.ui.text.UiText
import com.artemissoftware.pokeconnect.features.navigation.NavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    private var pokemonId = ""

    init {
        savedStateHandle.get<String>(NavArguments.ID)?.let {
            pokemonId = it
            getPokemon(it)
        }
    }

    fun onTriggerEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.UpdateFavorite -> updateFavorite()
            is DetailEvent.UpdateSelectedTab -> updateSelectedTab(index = event.index)
        }
    }

    private fun getPokemon(id: String) = with(_state) {

        updateLoading(true)

        viewModelScope.launch {
            getPokemonUseCase(query = id)
                .onSuccess { pokemon ->
                    update {
                        it.copy(pokemon = pokemon, isLoading = false, error = null)
                    }
                }
                .onFailure { error ->
                    update {
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
                update {
                    it.copy(pokemon = pokemon)
                }
            }
        }
    }


    private fun updateSelectedTab(index: Int) = with(_state) {
        update {
            it.copy(selectedTabIndex = index)
        }
    }

    private fun updateLoading(loading: Boolean) = with(_state) {
        update {
            it.copy(isLoading = loading)
        }
    }
}