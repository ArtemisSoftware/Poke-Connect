package com.artemissoftware.pokeconnect.core.data.repository

import com.artemissoftware.pokeconnect.core.data.mappers.toSearchHistoryEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toSearchResult
import com.artemissoftware.pokeconnect.core.database.dao.SearchHistoryDao
import com.artemissoftware.pokeconnect.core.models.search.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
): SearchHistoryRepository {

    override suspend fun addSearch(searchResult: SearchResult) {
        searchHistoryDao.insert(searchResult.toSearchHistoryEntity())
    }

    override fun getHistory(): Flow<List<SearchResult>> {
        return searchHistoryDao.getAll().map { list ->
            list.map { it.toSearchResult() }
        }
    }
}