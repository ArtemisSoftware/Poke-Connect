package com.artemissoftware.pokeconnect.domain.pokedex.usecases

import com.artemissoftware.pokeconnect.data.pokedex.repositories.PokedexRepository
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(private val pokedexRepository: PokedexRepository) {
    operator fun invoke() = pokedexRepository.getPokedex()
}
