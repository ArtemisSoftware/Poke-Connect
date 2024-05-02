package com.artemissoftware.pokeconnect.core.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.pokeconnect.core.data.HandleNetwork
import com.artemissoftware.pokeconnect.core.data.mappers.toAbilitiesEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.mappers.toPokemon
import com.artemissoftware.pokeconnect.core.data.mappers.toStatsEntity
import com.artemissoftware.pokeconnect.core.data.pagination.PokemonListPagingSource
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
        return HandleNetwork.safeNetworkCall {
            pokeApiSource.getPokemon(query = query).toPokemon()
        }
    }

    override suspend fun save(pokemon: Pokemon) {
        pokemonDao.insert(
            pokemonEntity = pokemon.toEntity(),
            abilityEntities = pokemon.toAbilitiesEntity(),
            statsEntities = pokemon.toStatsEntity(),
        )
    }

    override suspend fun delete(pokemon: Pokemon) {
        pokemonDao.delete(pokemon.toEntity())
    }

    override fun getAll(): Flow<PagingData<PokedexEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
            ),
            pagingSourceFactory = {
                pokemonDao.getAll()
            },
        ).flow
            .map { value: PagingData<PokemonEntity> ->
                value.map { it.toPokedexEntry() }
            }
    }

    override fun search(query: String): Flow<PagingData<PokedexEntry>> {
        val (id, name) = getIdAndName(query)
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
            ),
            pagingSourceFactory = {
                pokemonDao.findPokemonByIdOrName(id = id, name = name)
            },
        ).flow
            .map { value: PagingData<PokemonEntity> ->
                value.map { it.toPokedexEntry() }
            }
    }

    private fun getIdAndName(query: String): Pair<Int, String>{
        val id: Int = query.toIntOrNull() ?: 0
        return id to query
    }
}