package com.artemissoftware.pokeconnect.domain.favorites.usecases

import com.artemissoftware.pokeconnect.data.favorites.repositories.FavoritesRepository
import com.artemissoftware.pokeconnect.core.models.Pokemon
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(pokemon: Pokemon){
        if(pokemon.isFavorite){
            favoritesRepository.delete(pokemon = pokemon)
        }
        else {
            favoritesRepository.save(pokemon = pokemon)
        }
    }
}