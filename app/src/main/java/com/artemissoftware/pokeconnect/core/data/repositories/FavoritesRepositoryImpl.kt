package com.artemissoftware.pokeconnect.core.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.pokeconnect.core.data.mappers.toAbilitiesEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.mappers.toStatsEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toTypesEntity
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import com.artemissoftware.pokeconnect.core.database.entities.PokemonEntity
import com.artemissoftware.pokeconnect.core.domain.repositories.FavoritesRepository
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
) : FavoritesRepository{
    override suspend fun save(pokemon: Pokemon) {
        pokemonDao.insert(
            pokemonEntity = pokemon.toEntity(),
            abilityEntities = pokemon.toAbilitiesEntity(),
            statsEntities = pokemon.toStatsEntity(),
            typesEntities = pokemon.toTypesEntity()
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
                pokemonDao.findPagedPokemonByIdOrName(id = id, name = name)
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