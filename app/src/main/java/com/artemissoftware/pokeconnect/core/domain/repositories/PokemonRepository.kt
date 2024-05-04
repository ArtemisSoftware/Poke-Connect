package com.artemissoftware.pokeconnect.core.domain.repositories

import androidx.paging.PagingData
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokedex(): Flow<PagingData<PokedexEntry>>

    suspend fun searchPokedex(query: String): Resource<List<Pokemon>>
}