package com.artemissoftware.pokeconnect.core.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.pokeconnect.core.data.HandleNetwork
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.mappers.toPokemon
import com.artemissoftware.pokeconnect.core.data.pagination.PokemonListPagingSource
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiSource: PokeApiSource,
    private val pokemonDao: PokemonDao,
) : PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<PokedexEntry>> {
        return Pager(
            config = PagingConfig(pageSize = PokeApi.PAGE_SIZE),
            pagingSourceFactory = {
                PokemonListPagingSource(
                    pokeApiSource = pokeApiSource,
                )
            },
        ).flow
            .map { value: PagingData<PokedexEntryDto> ->
                value.map { it.toPokedexEntry() }
            }
    }

    override suspend fun getPokemon(query: String): Resource<Pokemon> {

        val (id, name) = getIdAndName(query)
        val pokemon = pokemonDao.getPokemon(id)

        if(pokemon != null){
            return Resource.Success(pokemon.toPokemon())
        }

        return HandleNetwork.safeNetworkCall {
            pokeApiSource.getPokemon(query = query.lowercase(Locale.ROOT)).toPokemon()
        }
    }



    private fun getIdAndName(query: String): Pair<Int, String>{
        val id: Int = query.toIntOrNull() ?: 0
        return id to query
    }
}