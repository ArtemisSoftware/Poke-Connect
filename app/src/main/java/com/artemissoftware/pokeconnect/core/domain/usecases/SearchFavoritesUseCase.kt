package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import javax.inject.Inject

class SearchFavoritesUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(query: String) = pokemonRepository.search(query)
}