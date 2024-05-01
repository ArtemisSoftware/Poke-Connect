package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    operator fun invoke() = pokemonRepository.getAll()
}