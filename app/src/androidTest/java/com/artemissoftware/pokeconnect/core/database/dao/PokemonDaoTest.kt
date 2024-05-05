package com.artemissoftware.pokeconnect.core.database.dao

import androidx.paging.PagingSource
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.TestInstrumentedData
import com.artemissoftware.pokeconnect.TestInstrumentedData.abilityEntity
import com.artemissoftware.pokeconnect.TestInstrumentedData.pokemonEntity
import com.artemissoftware.pokeconnect.TestInstrumentedData.statEntry
import com.artemissoftware.pokeconnect.TestInstrumentedData.typesEntity
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import com.artemissoftware.pokeconnect.core.database.dao.PokemonDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import javax.inject.Inject

@HiltAndroidTest
class PokemonDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var database: PokemonDataBase
    private lateinit var pokemonDao: PokemonDao

    @Before
    fun setUp() {
        hiltRule.inject()
        pokemonDao = database.getPokemonDao()
    }

    @After
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

        val result = pokemonDao.getPokemon(pokemonEntity.id, pokemonEntity.name)

        assertThat(result[0]).isEqualTo(TestInstrumentedData.pokemonRelation)
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
    fun `Find paged pokemon list by id and by name`() = runTest {
        pokemonDao.insert(
            pokemonEntity = pokemonEntity,
            statsEntities = listOf(statEntry),
            abilityEntities = listOf(abilityEntity),
            typesEntities = listOf(typesEntity),
        )

        val pagingSource = pokemonDao.findPagedPokemonByIdOrName(pokemonEntity.id, pokemonEntity.name)

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
        val result = pokemonDao.getPokemon(pokemonEntity.id, pokemonEntity.name)

        assertThat(result).isEmpty()
    }
}
