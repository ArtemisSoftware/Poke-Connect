package com.artemissoftware.pokeconnect.core.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.core.data.pagination.PokemonListPagingSource
import com.artemissoftware.pokeconnect.core.domain.repositories.PokemonRepository
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.PokedexEntryDto
import com.artemissoftware.pokeconnect.core.network.source.PokeApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiSource: PokeApiSource
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
}