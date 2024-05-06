package com.artemissoftware.pokeconnect.core.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.data.mappers.toSearchHistoryEntity
import com.artemissoftware.pokeconnect.core.data.mappers.toSearchResult
import com.artemissoftware.pokeconnect.testdata.SearchHistoryTestMockData.searchHistoryEntity
import com.artemissoftware.pokeconnect.testdata.SearchHistoryTestMockData.searchResult
import org.junit.jupiter.api.Test

class SearchHistoryMapperTest {
    @Test
    fun `Map SearchHistoryEntity to SearchResult`() {
        assertThat(searchHistoryEntity.toSearchResult()).isEqualTo(searchResult)
    }

    @Test
    fun `Map SearchResult to SearchHistoryEntity`() {
        assertThat(searchResult.toSearchHistoryEntity()).isEqualTo(searchHistoryEntity)
    }
}