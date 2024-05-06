package com.artemissoftware.pokeconnect.domain.favorites.usecases

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isNotEmpty
import com.artemissoftware.pokeconnect.testdata.TestMockData
import com.artemissoftware.pokeconnect.core.data.fakes.FakeFavoritesRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach

class UpdateFavoriteUseCaseTest {

    private lateinit var favoritesRepository: FakeFavoritesRepository

    private lateinit var updateFavoriteUseCase: UpdateFavoriteUseCase

    @BeforeEach
    fun setup() {
        favoritesRepository = FakeFavoritesRepository()
        updateFavoriteUseCase = UpdateFavoriteUseCase(favoritesRepository = favoritesRepository)
    }

    @org.junit.jupiter.api.Test
    fun `save a pokemon that is not a favorite`() = runTest {

        updateFavoriteUseCase(TestMockData.getPokemon(isFavorite = false))

        assertThat(favoritesRepository.pokemonList).isNotEmpty()
    }

    @org.junit.jupiter.api.Test
    fun `delete a pokemon that is a favorite`() = runTest {

        updateFavoriteUseCase(TestMockData.getPokemon(isFavorite = false))
        updateFavoriteUseCase(TestMockData.getPokemon(isFavorite = true))

        assertThat(favoritesRepository.pokemonList).isEmpty()
    }
}