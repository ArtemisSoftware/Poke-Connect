package com.artemissoftware.pokeconnect.database.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.artemissoftware.pokeconnect.TestInstrumentedData.abilityEntity
import com.artemissoftware.pokeconnect.TestInstrumentedData.pokemonEntity
import com.artemissoftware.pokeconnect.TestInstrumentedData.pokemonRelation
import com.artemissoftware.pokeconnect.TestInstrumentedData.statEntry
import com.artemissoftware.pokeconnect.TestInstrumentedData.typesEntity
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PokemonDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: PokemonDataBase
    private lateinit var pokemonDao: PokemonDao

    @BeforeEach
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, PokemonDataBase::class.java)
            .allowMainThreadQueries()
            .build()

        pokemonDao = database.getPokemonDao()
    }

    @AfterEach
    fun tearDown() {
        database.close()
    }

    @Test
    fun `Insert pokemon`() = runTest {
        pokemonDao.insert(
            pokemonEntity = pokemonEntity,
            statsEntities = listOf(statEntry),
            abilityEntities = listOf(abilityEntity),
            typesEntities = listOf(typesEntity),
        )

        val result = pokemonDao.getPokemon(pokemonEntity.id)

        assertThat(result).isEqualTo(pokemonRelation)
    }

    @Test
    fun `Insert pokemon and get all`() = runTest {
        pokemonDao.insert(
            pokemonEntity = pokemonEntity,
            statsEntities = listOf(statEntry),
            abilityEntities = listOf(abilityEntity),
            typesEntities = listOf(typesEntity),
        )

        val pagingSource = pokemonDao.getAll()

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        Assertions.assertTrue { result is PagingSource.LoadResult.Page }
        assertThat(1).isEqualTo((result as PagingSource.LoadResult.Page).data.size)
        assertThat(listOf(pokemonEntity)).isEqualTo(result.data)
    }

    @Test
    fun `Find pokemon by id and by name`() = runTest {
        pokemonDao.insert(
            pokemonEntity = pokemonEntity,
            statsEntities = listOf(statEntry),
            abilityEntities = listOf(abilityEntity),
            typesEntities = listOf(typesEntity),
        )

        val pagingSource = pokemonDao.findPokemonByIdOrName(pokemonEntity.id, pokemonEntity.name)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        Assertions.assertTrue { result is PagingSource.LoadResult.Page }
        assertThat(1).isEqualTo((result as PagingSource.LoadResult.Page).data.size)
        assertThat(listOf(pokemonEntity)).isEqualTo(result.data)
    }

    @Test
    fun `Delete pokemon`() = runTest {
        pokemonDao.insert(
            pokemonEntity = pokemonEntity,
            statsEntities = listOf(statEntry),
            abilityEntities = listOf(abilityEntity),
            typesEntities = listOf(typesEntity),
        )

        pokemonDao.delete(pokemonEntity)
        val result = pokemonDao.getPokemon(pokemonEntity.id)

        assertThat(result).isNull()
    }

    /*
    @Test
    fun `Add shopping item`() = runTest {
        shoppingDao.insertShoppingItem(TestShoppingItemData.shoppingItem)

        val allShoppingItem = shoppingDao.getAllShoppingItems()

        allShoppingItem.test {
            val list = awaitItem()
            assertThat(list).isEqualTo(listOf(TestShoppingItemData.shoppingItem))
            cancel()
        }
    }

    @Test
    fun `Delete shopping item`() = runTest {
        shoppingDao.insertShoppingItem(TestShoppingItemData.shoppingItem)
        shoppingDao.deleteShoppingItem(TestShoppingItemData.shoppingItem)

        val allShoppingItem = shoppingDao.getAllShoppingItems()

        allShoppingItem.test {
            val list = awaitItem()

            assertThat(list).all {
                doesNotContain(TestShoppingItemData.shoppingItem)
                hasSize(0)
            }
            cancel()
        }
    }

    @Test
    fun `Insert items and check total price sum`() = runTest {
        TestShoppingItemData.shoppingItemList.forEach { shoppingItem ->
            shoppingDao.insertShoppingItem(shoppingItem)
        }

        val totalPriceSum = shoppingDao.getTotalPrice()
        totalPriceSum.test {
            val total = awaitItem()
            assertThat(total)
                .isEqualTo(TestShoppingItemData.shoppingItemList.map { it.price * it.amount }.sum())
        }
    }
    */
}