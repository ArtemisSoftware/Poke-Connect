package com.artemissoftware.pokeconnect.features.pokedex

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import com.artemissoftware.pokeconnect.testdata.TestMockData
import com.artemissoftware.pokeconnect.testdata.TestMockData.errorDescription
import com.artemissoftware.pokeconnect.core.data.fakes.FakePokedexRepository
import com.artemissoftware.pokeconnect.core.data.fakes.FakeSearchHistoryRepository
import com.artemissoftware.pokeconnect.core.domain.usecases.GetSearchHistoryUseCase
import com.artemissoftware.pokeconnect.core.domain.usecases.UpdateSearchHistoryUseCase
import com.artemissoftware.pokeconnect.core.ui.text.UiText
import com.artemissoftware.pokeconnect.domain.pokedex.usecases.GetPokedexUseCase
import com.artemissoftware.pokeconnect.domain.pokedex.usecases.SearchPokemonUseCase
import com.artemissoftware.pokeconnect.testdata.SearchHistoryTestMockData
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
class PokedexViewModelTest {

    private lateinit var viewModel: PokedexViewModel

    private lateinit var pokedexRepository: FakePokedexRepository
    private lateinit var searchHistoryRepository: FakeSearchHistoryRepository

    private lateinit var getPokedexUseCase: GetPokedexUseCase
    private lateinit var searchPokemonUseCase: SearchPokemonUseCase
    private lateinit var getSearchHistoryUseCase: GetSearchHistoryUseCase
    private lateinit var updateSearchHistoryUseCase: UpdateSearchHistoryUseCase

//    @OptIn(ExperimentalCoroutinesApi::class)
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher = StandardTestDispatcher()
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        pokedexRepository = FakePokedexRepository()
        searchHistoryRepository = FakeSearchHistoryRepository()

        getPokedexUseCase = GetPokedexUseCase(pokedexRepository = pokedexRepository)
        searchPokemonUseCase = SearchPokemonUseCase(pokedexRepository = pokedexRepository)
        getSearchHistoryUseCase = GetSearchHistoryUseCase(searchHistoryRepository = searchHistoryRepository)
        updateSearchHistoryUseCase = UpdateSearchHistoryUseCase(searchHistoryRepository = searchHistoryRepository)

        viewModel = PokedexViewModel(
            getPokedexUseCase = getPokedexUseCase,
            searchPokemonUseCase = searchPokemonUseCase,
            getSearchHistoryUseCase = getSearchHistoryUseCase,
            updateSearchHistoryUseCase = updateSearchHistoryUseCase
        )
    }

    @AfterEach
    fun teardown() {
        Dispatchers.resetMain()  // Reset after each test to avoid side effects
    }


    @org.junit.jupiter.api.Test
    fun `when ActivateSearch is triggered with value check if isSearching has new value`() {
        val isSearching = false

        viewModel.onTriggerEvent(PokedexEvent.ActivateSearch(isActive = isSearching))

        assertThat(viewModel.state.value.isSearching).isEqualTo(isSearching)
    }

    @org.junit.jupiter.api.Test
    fun `when UpdateSearchQuery is triggered with value check if searchQuery has new value`() {
        val searchQuery = "bulbasaur"

        viewModel.onTriggerEvent(PokedexEvent.UpdateSearchQuery(searchQuery = searchQuery))

        assertThat(viewModel.state.value.searchQuery).isEqualTo(searchQuery)
    }

    @org.junit.jupiter.api.Test
    fun `when ClearSearch is triggered check if searchQuery and isSearching are cleared`() {

        viewModel.onTriggerEvent(PokedexEvent.ActivateSearch(isActive = true))
        viewModel.onTriggerEvent(PokedexEvent.UpdateSearchQuery(searchQuery = "bulbasaur"))
        viewModel.onTriggerEvent(PokedexEvent.ClearSearch)

        assertThat(viewModel.state.value.searchQuery).isEmpty()
        assertThat(viewModel.state.value.isSearching).isFalse()
    }


    @org.junit.jupiter.api.Test
    fun `when multiple searches are triggered check searchHistory is updated`() = runTest {

        viewModel.onTriggerEvent(PokedexEvent.UpdateSearchQuery(searchQuery = SearchHistoryTestMockData.searchResultList[0].description))
        viewModel.onTriggerEvent(PokedexEvent.SearchPokemon)

        advanceUntilIdle()

        viewModel.onTriggerEvent(PokedexEvent.UpdateSearchQuery(searchQuery = SearchHistoryTestMockData.searchResultList[1].description))
        viewModel.onTriggerEvent(PokedexEvent.SearchPokemon)

        advanceUntilIdle()

        viewModel.onTriggerEvent(PokedexEvent.UpdateSearchQuery(searchQuery = SearchHistoryTestMockData.searchResultList[0].description))
        viewModel.onTriggerEvent(PokedexEvent.SearchPokemon)

        advanceUntilIdle()
        assertThat(viewModel.state.value.searchHistory).isEqualTo(
            listOf(
                SearchHistoryTestMockData.searchResultList[1],
                SearchHistoryTestMockData.searchResultList[0]
            )
        )
    }

    @org.junit.jupiter.api.Test
    fun `when SearchPokemon is triggered check searchResult is updated`() = runTest {

        pokedexRepository.setPokemonFromApi()

        viewModel.onTriggerEvent(PokedexEvent.UpdateSearchQuery(searchQuery = "bulbasaur"))
        viewModel.onTriggerEvent(PokedexEvent.SearchPokemon)

        advanceUntilIdle()

        assertThat(viewModel.state.value.searchResult).isEqualTo(listOf(TestMockData.pokemonFromApi))
    }

    @org.junit.jupiter.api.Test
    fun `when SearchPokemon is triggered and error occurs check errorData is updated`() = runTest {

        pokedexRepository.shouldReturnError = true

        viewModel.onTriggerEvent(PokedexEvent.UpdateSearchQuery(searchQuery = "bulbasaur"))
        viewModel.onTriggerEvent(PokedexEvent.SearchPokemon)

        advanceUntilIdle()

        assertThat(viewModel.state.value.error?.message).isEqualTo(UiText.DynamicString(errorDescription))

    }
}