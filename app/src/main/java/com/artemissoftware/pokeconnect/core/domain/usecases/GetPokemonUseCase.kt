package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(query: String) = pokemonRepository.getPokemon(query = query)
}