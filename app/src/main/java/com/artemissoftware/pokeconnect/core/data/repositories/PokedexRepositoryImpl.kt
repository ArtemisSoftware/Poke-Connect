package com.artemissoftware.pokeconnect.core.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.pokeconnect.core.data.HandleNetwork
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.mappers.toPokemon
import com.artemissoftware.pokeconnect.core.data.pagination.PokedexPagingSource
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.database.relations.PokemonRelation
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.domain.repositories.PokedexRepository
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val pokeApiSource: PokeApiSource,
    private val pokemonDao: PokemonDao,
) : PokedexRepository {

    override fun getPokedex(): Flow<PagingData<PokedexEntry>> {
        return Pager(
            config = PagingConfig(pageSize = PokeApi.PAGE_SIZE),
            pagingSourceFactory = {
                PokedexPagingSource(
                    pokeApiSource = pokeApiSource,
                )
            },
        ).flow
            .map { value: PagingData<PokedexEntryDto> ->
                value.map { it.toPokedexEntry() }
            }
    }

    override suspend fun searchPokedex(query: String): Resource<List<Pokemon>> {
        val searchQuery = query.lowercase(Locale.ROOT)

        val result = searchLocally(searchQuery)

        return if(result.isNotEmpty()){
            Resource.Success(result.map { it.toPokemon() })
        }
        else {
            searchRemotely(query = searchQuery)
        }
    }

    private suspend fun searchLocally(query: String): List<PokemonRelation>{
        val (id, name) = getIdAndName(query)
        return pokemonDao.getPokemon(id = id, name = name)
    }

    private suspend fun searchRemotely(query: String): Resource<List<Pokemon>> {
        return coroutineScope {

            val deferredPokemon = async {
                HandleNetwork.safeNetworkCall {
                    pokeApiSource.getPokemon(query = query.lowercase(Locale.ROOT))
                }
            }

            val deferredSpecies = async {
                HandleNetwork.safeNetworkCall {
                    pokeApiSource.getSpecies(query = query.lowercase(Locale.ROOT))
                }
            }

            // Await the results of both async tasks
            val resultPokemon = deferredPokemon.await()
            val resultSpecies = deferredSpecies.await()

            when{
                resultPokemon is Resource.Success && resultSpecies is Resource.Success ->{
                    Resource.Success(listOf(resultPokemon.data.toPokemon(resultSpecies.data)))
                }
                resultPokemon is Resource.Success ->{
                    Resource.Success(listOf(resultPokemon.data.toPokemon()))
                }
                else -> {
                    Resource.Failure((resultPokemon as Resource.Failure).error)
                }
            }
        }
    }

    private fun getIdAndName(query: String): Pair<Int, String>{
        val id: Int = query.toIntOrNull() ?: 0
        return id to query
    }
}