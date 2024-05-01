package com.artemissoftware.pokeconnect.core.domain.usecases

import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import com.artemissoftware.pokeconnect.core.models.Pokemon
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(pokemon: Pokemon, isFavorite: Boolean){
        if(isFavorite){
            pokemonRepository.delete(pokemon = pokemon)
        }
        else {
            pokemonRepository.save(pokemon = pokemon)
        }
    }
}