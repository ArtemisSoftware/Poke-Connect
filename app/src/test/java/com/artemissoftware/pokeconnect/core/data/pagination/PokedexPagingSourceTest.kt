package com.artemissoftware.pokeconnect.core.data.pagination

import androidx.paging.PagingSource
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.TestMockData.pokedexPageDto
import com.artemissoftware.pokeconnect.core.data.pagination.PokedexPagingSource
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import com.artemissoftware.pokeconnect.core.data.fakes.FakePokeApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PokedexPagingSourceTest {

    private lateinit var pokeApi: FakePokeApi
    private lateinit var pokeApiSource: PokeApiSource

    @BeforeEach
    fun setup() {
        pokeApi = FakePokeApi()
        pokeApiSource =  PokeApiSource(pokeApi = pokeApi)
    }

    @Test
    fun `Request pokemon page, expect multiple result, assert LoadResult_Page`() = runTest {
        val currentPage = 0
        val endOfPaginationReached =  currentPage * PokeApi.PAGE_SIZE >= pokedexPageDto.count
        val nextKey = if (endOfPaginationReached) null else currentPage + 1

        val pokemonListPagingSource = PokedexPagingSource(pokeApiSource = pokeApiSource)

        assertThat(
            PagingSource.LoadResult.Page(
                data = pokedexPageDto.results,
                prevKey = null,
                nextKey = nextKey
            )
        ).isEqualTo(
            pokemonListPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = pokedexPageDto.results.size,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `Request pokemon page, get error, assert LoadResult_Error`() = runTest {
        pokeApi.callShouldReturnError = true
        val pokemonListPagingSource = PokedexPagingSource(pokeApiSource = pokeApiSource)
        val loadResult = pokemonListPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = pokedexPageDto.results.size,
                placeholdersEnabled = false
            )
        )

        assertTrue { loadResult is PagingSource.LoadResult.Error }
    }
}