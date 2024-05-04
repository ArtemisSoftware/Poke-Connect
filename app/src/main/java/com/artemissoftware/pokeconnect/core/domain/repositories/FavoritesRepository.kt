package com.artemissoftware.pokeconnect.core.domain.repositories

import androidx.paging.PagingData
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun save(pokemon: Pokemon)

    suspend fun delete(pokemon: Pokemon)

    fun getAll(): Flow<PagingData<PokedexEntry>>

    fun search(query: String): Flow<PagingData<PokedexEntry>>
}