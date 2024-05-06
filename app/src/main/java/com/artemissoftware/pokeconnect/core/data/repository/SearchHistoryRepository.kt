package com.artemissoftware.pokeconnect.core.data.repository

import com.artemissoftware.pokeconnect.core.models.search.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    suspend fun addSearch(searchResult: SearchResult)

    fun getHistory(): Flow<List<SearchResult>>
}