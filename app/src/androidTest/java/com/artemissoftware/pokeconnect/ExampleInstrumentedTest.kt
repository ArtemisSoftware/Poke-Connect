package com.artemissoftware.pokeconnect

import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.database.PokemonDataBase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: PokemonDataBase

    @Before
    open fun setUp() {
        hiltRule.inject()
        database.clearAllTables()
    }

    @After
    open fun tearDown() {
        database.close()
    }

    @Test
    fun useAppContext() = runTest {
        database.getPokemonDao().insert(
            pokemonEntity = TestInstrumentedData.pokemonEntity,
            statsEntities = listOf(TestInstrumentedData.statEntry),
            abilityEntities = listOf(TestInstrumentedData.abilityEntity),
            typesEntities = listOf(TestInstrumentedData.typesEntity),
        )
        val result = database.getPokemonDao().getPokemon(TestInstrumentedData.pokemonEntity.id, TestInstrumentedData.pokemonEntity.name)

        Assert.assertEquals(1, result.size)
        assertThat(result[0]).isEqualTo(TestInstrumentedData.pokemonRelation)
        //println(database.getPokemonDao())
        //assert(true)
    }

//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.artemissoftware.pokeconnect", appContext.packageName)
//    }
}