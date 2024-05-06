package com.artemissoftware.pokeconnect.core.data.repository

import com.artemissoftware.pokeconnect.core.database.dao.SearchHistoryDao
import com.artemissoftware.pokeconnect.testdata.SearchHistoryTestMockData.searchResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchHistoryRepositoryImplTest {

    private lateinit var repository: SearchHistoryRepositoryImpl

    private lateinit var searchHistoryDao: SearchHistoryDao

    @BeforeEach
    fun setUp() {
        searchHistoryDao = mockk(relaxed = true)
        repository = SearchHistoryRepositoryImpl(searchHistoryDao = searchHistoryDao)
    }

    @Test
    fun `Insert new search history`() = runBlocking {

        coEvery { searchHistoryDao.existWithFullData(any()) } returns false

        repository.addSearch(searchResult = searchResult)

        coVerify { searchHistoryDao.insert(any()) }
    }

    @Test
    fun `Update timestamp for a search related to an existing search`() = runBlocking {

        coEvery { searchHistoryDao.existWithFullData(any()) } returns true

        repository.addSearch(searchResult = searchResult)

        coVerify { searchHistoryDao.updateTimeStamp(any(), any()) }
    }
}