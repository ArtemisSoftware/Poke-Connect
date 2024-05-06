package com.artemissoftware.pokeconnect.features.detail

import androidx.lifecycle.SavedStateHandle
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.artemissoftware.pokeconnect.TestMockData
import com.artemissoftware.pokeconnect.domain.pokedex.usecases.SearchPokemonUseCase
import com.artemissoftware.pokeconnect.domain.favorites.usecases.UpdateFavoriteUseCase
import com.artemissoftware.pokeconnect.core.ui.text.UiText
import com.artemissoftware.pokeconnect.core.data.fakes.FakeFavoritesRepository
import com.artemissoftware.pokeconnect.core.data.fakes.FakePokedexRepository
import com.artemissoftware.pokeconnect.features.navigation.NavArguments
import io.mockk.every
import io.mockk.mockk
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
class DetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

//    @ExperimentalCoroutinesApi
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var updateFavoriteUseCase: UpdateFavoriteUseCase
    private lateinit var searchPokemonUseCase: SearchPokemonUseCase

    private lateinit var pokedexRepository: FakePokedexRepository
    private lateinit var favoritesRepository: FakeFavoritesRepository
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: DetailViewModel

    @BeforeEach
    fun setup() {

        Dispatchers.setMain(testDispatcher)  // Set the main dispatcher to the test dispatcher

        pokedexRepository = FakePokedexRepository()
        favoritesRepository = FakeFavoritesRepository()

        updateFavoriteUseCase = UpdateFavoriteUseCase(favoritesRepository = favoritesRepository)
        searchPokemonUseCase = SearchPokemonUseCase(pokedexRepository = pokedexRepository)

        savedStateHandle = mockk(relaxed = true)

        every { savedStateHandle.get<String>(NavArguments.ID) } returns "1"
        every { savedStateHandle.get<DetailState>("state") } returns DetailState()

        viewModel = DetailViewModel(
            searchPokemonUseCase = searchPokemonUseCase,
            updateFavoriteUseCase = updateFavoriteUseCase,
            savedStateHandle = savedStateHandle,
        )
    }

    @AfterEach
    fun teardown() {
        Dispatchers.resetMain()  // Reset after each test to avoid side effects
    }

    @org.junit.jupiter.api.Test
    fun `when Loading pokemon from navigation is triggered and no id is found update error data`() = runTest {
        every { savedStateHandle.get<String>(NavArguments.ID) } returns null

        val searchPokemonUseCase = SearchPokemonUseCase(pokedexRepository = pokedexRepository)
        val viewModel = DetailViewModel(
            searchPokemonUseCase = searchPokemonUseCase,
            updateFavoriteUseCase = updateFavoriteUseCase,
            savedStateHandle = savedStateHandle,
        )

        advanceUntilIdle()

        assertThat(viewModel.state.value.error?.message).isNotNull()
    }

    @org.junit.jupiter.api.Test
    fun `when UpdateSelectedTab is triggered with value check if selectedTabIndex has new value`() = runTest {
        val index = 1

        viewModel.onTriggerEvent(DetailEvent.UpdateSelectedTab(index = index))

        assertThat(viewModel.state.value.selectedTabIndex).isEqualTo(index)
    }

    @org.junit.jupiter.api.Test
    fun `when Searching for pokemon is triggered and returns error should update error data`() = runTest {
        pokedexRepository.shouldReturnError = true

        val searchPokemonUseCase = SearchPokemonUseCase(pokedexRepository = pokedexRepository)
        val viewModel = DetailViewModel(
            searchPokemonUseCase = searchPokemonUseCase,
            updateFavoriteUseCase = updateFavoriteUseCase,
            savedStateHandle = savedStateHandle,
        )

        advanceUntilIdle()

        assertThat(viewModel.state.value.error?.message).isEqualTo(UiText.DynamicString(TestMockData.errorDescription))
    }

    @org.junit.jupiter.api.Test
    fun `when UpdateFavorite is triggered with pokemon from api should be marked as favorite`() = runTest {
        pokedexRepository.setPokemonFromApi()

        val searchPokemonUseCase = SearchPokemonUseCase(pokedexRepository = pokedexRepository)
        val viewModel = DetailViewModel(
            searchPokemonUseCase = searchPokemonUseCase,
            updateFavoriteUseCase = updateFavoriteUseCase,
            savedStateHandle = savedStateHandle,
        )

        viewModel.onTriggerEvent(DetailEvent.UpdateFavorite)
        advanceUntilIdle()

        assertThat(viewModel.state.value.pokemon?.isFavorite).isEqualTo(true)
    }

    @org.junit.jupiter.api.Test
    fun `when UpdateFavorite is triggered with pokemon from db should be marked as not favorite`() = runTest {
        pokedexRepository.setPokemonFromDb()

        val searchPokemonUseCase = SearchPokemonUseCase(pokedexRepository = pokedexRepository)
        val viewModel = DetailViewModel(
            searchPokemonUseCase = searchPokemonUseCase,
            updateFavoriteUseCase = updateFavoriteUseCase,
            savedStateHandle = savedStateHandle,
        )

        viewModel.onTriggerEvent(DetailEvent.UpdateFavorite)
        advanceUntilIdle()

        assertThat(viewModel.state.value.pokemon?.isFavorite).isEqualTo(false)
    }
}