package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.domain.repositories.PokedexRepository
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(private val pokedexRepository: PokedexRepository) {
    operator fun invoke() = pokedexRepository.getPokedex()
}
