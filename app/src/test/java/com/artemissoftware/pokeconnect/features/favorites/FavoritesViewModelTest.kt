package com.artemissoftware.pokeconnect.features.favorites

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.domain.usecases.GetFavoritesUseCase
import com.artemissoftware.pokeconnect.core.domain.usecases.SearchFavoritesUseCase
import com.artemissoftware.pokeconnect.data.fakes.FakeFavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest {

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var favoritesRepository: FakeFavoritesRepository

    private lateinit var getFavoritesUseCase: GetFavoritesUseCase
    private lateinit var searchPokemonUseCase: SearchFavoritesUseCase

    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        favoritesRepository = FakeFavoritesRepository()
        getFavoritesUseCase = GetFavoritesUseCase(favoritesRepository = favoritesRepository)
        searchPokemonUseCase = SearchFavoritesUseCase(favoritesRepository = favoritesRepository)

        viewModel = FavoritesViewModel(
            getFavoritesUseCase = getFavoritesUseCase,
            searchFavoritesUseCase = searchPokemonUseCase,
        )
    }

    @AfterEach
    fun teardown() {
        Dispatchers.resetMain()  // Reset after each test to avoid side effects
    }

    @org.junit.jupiter.api.Test
    fun `when ActivateSearch is triggered with value check if isSearching has new value`() {
        val isSearching = false

        viewModel.onTriggerEvent(FavoriteEvent.ActivateSearch(isActive = isSearching))

        assertThat(isSearching).isEqualTo(viewModel.state.value.isSearching)
    }

    @org.junit.jupiter.api.Test
    fun `when UpdateSearchQuery is triggered with value check if searchQuery has new value`() {
        val searchQuery = "bulbasaur"

        viewModel.onTriggerEvent(FavoriteEvent.UpdateSearchQuery(searchQuery = searchQuery))

        assertThat(searchQuery).isEqualTo(viewModel.state.value.searchQuery)
    }

    @org.junit.jupiter.api.Test
    fun `when ClearSearch is triggered check if searchQuery and isSearching are cleared`() {

        viewModel.onTriggerEvent(FavoriteEvent.ActivateSearch(isActive = true))
        viewModel.onTriggerEvent(FavoriteEvent.UpdateSearchQuery(searchQuery = "bulbasaur"))
        viewModel.onTriggerEvent(FavoriteEvent.ClearSearch)

        assertThat("") .isEqualTo(viewModel.state.value.searchQuery)
        assertThat(false).isEqualTo(viewModel.state.value.isSearching)
    }

    @org.junit.jupiter.api.Test
    fun `_when multiple searches are triggered check if searchHistory is updated`() = runTest {
        val searchHistory = listOf("89", "bulbasaur")

        viewModel.onTriggerEvent(FavoriteEvent.UpdateSearchQuery(searchQuery = "bulbasaur"))
        viewModel.onTriggerEvent(FavoriteEvent.SearchPokemon)

        advanceUntilIdle()

        viewModel.onTriggerEvent(FavoriteEvent.UpdateSearchQuery(searchQuery = "89"))
        viewModel.onTriggerEvent(FavoriteEvent.SearchPokemon)

        advanceUntilIdle()

        viewModel.onTriggerEvent(FavoriteEvent.UpdateSearchQuery(searchQuery = "bulbasaur"))
        viewModel.onTriggerEvent(FavoriteEvent.SearchPokemon)

        advanceUntilIdle()
        assertThat(searchHistory).isEqualTo(viewModel.state.value.searchHistory)
    }

}