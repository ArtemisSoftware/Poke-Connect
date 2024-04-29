package com.artemissoftware.pokeconnect.core.domain.repositories

import androidx.paging.PagingData
import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokedexEntry>>
}