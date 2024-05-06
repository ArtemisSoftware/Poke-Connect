package com.artemissoftware.pokeconnect.core.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.testdata.TestMockData
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import org.junit.jupiter.api.Test

class PokedexMapperTest {

    @Test
    fun `Map PokedexEntryDto to PokedexEntry`() {
        assertThat(TestMockData.pokedexEntryDto.toPokedexEntry()).isEqualTo(TestMockData.pokedexEntry)
    }

}