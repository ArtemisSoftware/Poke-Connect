package com.artemissoftware.pokeconnect.core.data.fakes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokedexEntry
import com.artemissoftware.pokeconnect.data.favorites.repositories.FavoritesRepository
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import kotlinx.coroutines.flow.Flow

class FakeFavoritesRepository: FavoritesRepository {

    var pokemonList = mutableListOf<Pokemon>()

    override suspend fun save(pokemon: Pokemon) {
        pokemonList.add(pokemon)
    }

    override suspend fun delete(pokemon: Pokemon) {
        pokemonList.removeIf { pokemon.id == it.id }
    }

    override fun getAll(): Flow<PagingData<PokedexEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                InMemoryFavoritePagingSource(listOf(pokedexEntry), 1)
            }
        ).flow
    }

    override fun search(query: String): Flow<PagingData<PokedexEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                InMemoryFavoritePagingSource(listOf(pokedexEntry), 1)
            }
        ).flow
    }
}

private class InMemoryFavoritePagingSource(
    private val pokedexList: List<PokedexEntry>,
    private val pageSize: Int = 10
) : PagingSource<Int, PokedexEntry>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokedexEntry> {
        return try {
            val currentPage = params.key ?: 1

            val fromIndex = (currentPage - 1) * pageSize
            val toIndex = minOf(fromIndex + pageSize, pokedexList.size)

            val pageData = pokedexList

            val prevKey = if (currentPage > 1) currentPage - 1 else null
            val nextKey = if (toIndex < pokedexList.size) currentPage + 1 else null

            LoadResult.Page(
                data = pageData,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokedexEntry>): Int? {
        return state.anchorPosition?.let { position ->
            val closestPage = state.closestPageToPosition(position)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }
}