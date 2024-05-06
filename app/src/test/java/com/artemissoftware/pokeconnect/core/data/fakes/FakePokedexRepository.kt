package com.artemissoftware.pokeconnect.core.data.fakes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.pokeconnect.testdata.TestMockData.errorDescription
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokedexEntry
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokemon2
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokemonFromApi
import com.artemissoftware.pokeconnect.testdata.TestMockData.pokemonFromDb
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.domain.error.DataError
import com.artemissoftware.pokeconnect.core.models.pokemon.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.pokemon.Pokemon
import com.artemissoftware.pokeconnect.data.pokedex.repositories.PokedexRepository
import kotlinx.coroutines.flow.Flow

class FakePokedexRepository: PokedexRepository {

    var shouldReturnError = false

    private var pokemon = pokemonFromDb

    fun setPokemonFromDb(){
        pokemon = pokemonFromDb
    }

    fun setPokemonFromApi(){
        pokemon = pokemonFromApi
    }

    override fun getPokedex(): Flow<PagingData<PokedexEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                InMemoryPokemonPagingSource(listOf(pokedexEntry), 1)
            }
        ).flow
    }

    override suspend fun searchPokedex(query: String): Resource<List<Pokemon>> {
        return if(shouldReturnError){
            Resource.Failure(DataError.NetworkError.Error(errorDescription))
        }
        else {
            when(query){
                "1" -> Resource.Success(listOf(pokemon))
                "bulbasaur" -> Resource.Success(listOf(pokemon))
                "2" -> Resource.Success(listOf(pokemon2))
                "ivysayur" -> Resource.Success(listOf(pokemon2))
                else ->  Resource.Success(listOf(pokemon))
            }

        }
    }
}

private class InMemoryPokemonPagingSource(
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
