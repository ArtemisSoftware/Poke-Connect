package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import com.artemissoftware.pokeconnect.core.models.Pokemon
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(pokemon: Pokemon){
        if(pokemon.isFavorite){
            pokemonRepository.delete(pokemon = pokemon)
        }
        else {
            pokemonRepository.save(pokemon = pokemon)
        }
    }
}