package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.domain.repositories.PokedexRepository
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(private val pokedexRepository: PokedexRepository) {
    suspend operator fun invoke(query: String) = pokedexRepository.searchPokedex(query = query)
}