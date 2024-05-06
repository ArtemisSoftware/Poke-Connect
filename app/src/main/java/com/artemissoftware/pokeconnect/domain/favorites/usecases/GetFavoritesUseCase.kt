package com.artemissoftware.pokeconnect.domain.favorites.usecases

import com.artemissoftware.pokeconnect.data.favorites.repositories.FavoritesRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    operator fun invoke() = favoritesRepository.getAll()
}