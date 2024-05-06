package com.artemissoftware.pokeconnect.core.database.dao

import com.artemissoftware.pokeconnect.TestInstrumentedData.searchHistoryEntity
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class SearchHistoryDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var database: PokemonDataBase
    private lateinit var searchHistoryDao: SearchHistoryDao

    @Before
    fun setUp() {
        hiltRule.inject()
        searchHistoryDao = database.getSearchHistoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun `Insert SearchResult`() = runTest {
        searchHistoryDao.insert(searchHistoryEntity)

        val result = searchHistoryDao.getAll()

        Assert.assertEquals(1, result.first().size)
    }
}
