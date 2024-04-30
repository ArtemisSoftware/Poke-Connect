package com.artemissoftware.pokeconnect.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.data.mappers.toPokedexEntry
import com.artemissoftware.pokeconnect.data.TestMockData.pokedexEntry
import com.artemissoftware.pokeconnect.data.TestMockData.pokedexEntryDto
import org.junit.jupiter.api.Test

class PokemonMapperTest {

    @Test
    fun `Map PokedexEntryDto to PokedexEntry`() {
        assertThat(pokedexEntryDto.toPokedexEntry()).isEqualTo(pokedexEntry)
    }
}