package com.artemissoftware.pokeconnect.testdata

import com.artemissoftware.pokeconnect.core.database.entities.SearchHistoryEntity
import com.artemissoftware.pokeconnect.core.models.search.SearchResult

object SearchHistoryTestMockData {

    val searchHistoryEntity = SearchHistoryEntity(
        description = "1",
        note = "bulbasaur"
    )

    val searchResult = SearchResult(
        description = "1",
        note = "bulbasaur"
    )

    val searchResultList = listOf(
        searchResult,
        SearchResult(
            description = "2",
            note = "ivysaur"
        ),
        SearchResult(
            description = "batman",
        ),
    )
}