package com.artemissoftware.pokeconnect.core.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.pokeconnect.core.data.HandleNetwork
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.domain.error.DataError
import com.artemissoftware.pokeconnect.core.domain.exceptions.PaginationException
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource

class PokedexPagingSource(
    private val pokeApiSource: PokeApiSource,
) : PagingSource<Int, PokedexEntryDto>() {

    private var totalCount = 0

    override fun getRefreshKey(state: PagingState<Int, PokedexEntryDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokedexEntryDto> {
        val currentPage = params.key ?: 0

        val result = HandleNetwork.safeNetworkCall {
            pokeApiSource.getPokemonList(limit = PokeApi.PAGE_SIZE, offset = currentPage * PokeApi.PAGE_SIZE)
        }

        return when (result) {
            is Resource.Failure -> {
                LoadResult.Error(throwable = PaginationException(result.error as DataError.NetworkError))
            }
            is Resource.Success -> {
                val pokemonList = result.data
                totalCount += pokemonList.results.size

                var data = emptyList<PokedexEntryDto>()
                var prevKey: Int? = null
                var nextKey: Int? = null

                if (totalCount <= pokemonList.count) {
                    val endOfPaginationReached =  currentPage * PokeApi.PAGE_SIZE >= pokemonList.count
                    data = pokemonList.results
                    prevKey = if (currentPage == 0) null else currentPage - 1
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                }

                LoadResult.Page(
                    data = data,
                    prevKey = prevKey,
                    nextKey = nextKey,
                )
            }
        }
    }
}
