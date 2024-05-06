package com.artemissoftware.pokeconnect.core.data.fakes

import com.artemissoftware.pokeconnect.core.data.repository.SearchHistoryRepository
import com.artemissoftware.pokeconnect.core.models.search.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeSearchHistoryRepository(): SearchHistoryRepository {

    private var _searchResults = MutableStateFlow<List<SearchResult>>(emptyList())
    private var searchResults: StateFlow<List<SearchResult>> = _searchResults.asStateFlow()

    override suspend fun addSearch(searchResult: SearchResult) {
        val currentList = _searchResults.value.toMutableList()
        val index = currentList.indexOfFirst { it.description == searchResult.description }

        if (index >= 0) {
            currentList[index] = searchResult
        } else {
            currentList.add(searchResult)
        }

        _searchResults.value = currentList.sortedByDescending { it.description }
    }

    override fun getHistory(): Flow<List<SearchResult>> {
        return searchResults
    }
}