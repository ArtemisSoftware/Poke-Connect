package com.artemissoftware.pokeconnect.core.data.mappers

import com.artemissoftware.pokeconnect.core.database.entities.SearchHistoryEntity
import com.artemissoftware.pokeconnect.core.models.search.SearchResult

fun SearchHistoryEntity.toSearchResult(): SearchResult{
    return SearchResult(description = description, note = note)
}

fun SearchResult.toSearchHistoryEntity(): SearchHistoryEntity{
    return SearchHistoryEntity(description = description, note = note)
}

