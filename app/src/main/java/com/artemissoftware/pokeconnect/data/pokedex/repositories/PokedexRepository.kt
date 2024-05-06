package com.artemissoftware.pokeconnect.data.pokedex.repositories

import androidx.paging.PagingData
import com.artemissoftware.pokeconnect.core.domain.Resource
import com.artemissoftware.pokeconnect.core.models.pokemon.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun getPokedex(): Flow<PagingData<PokedexEntry>>

    suspend fun searchPokedex(query: String): Resource<List<Pokemon>>
}