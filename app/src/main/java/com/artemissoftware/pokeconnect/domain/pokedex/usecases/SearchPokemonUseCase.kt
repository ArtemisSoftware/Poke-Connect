package com.artemissoftware.pokeconnect.domain.pokedex.usecases

import com.artemissoftware.pokeconnect.data.pokedex.repositories.PokedexRepository
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(private val pokedexRepository: PokedexRepository) {
    suspend operator fun invoke(query: String) = pokedexRepository.searchPokedex(query = query)
}