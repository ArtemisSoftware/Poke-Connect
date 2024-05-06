package com.artemissoftware.pokeconnect.core.presentation.util.extensions

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.testdata.TestMockData
import com.artemissoftware.pokeconnect.testdata.TestMockData.stat
import com.artemissoftware.pokeconnect.core.models.Stat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class PokemonExtensionsTest{

    @ParameterizedTest
    @MethodSource("numberFormats")
    fun `Map stats to a max value`(input: List<Stat>, expected: Int) {

        assertThat(input.roundToNearestIncrement()).isEqualTo(expected)
    }

    private companion object {
        @JvmStatic
        fun numberFormats() = listOf(
            Arguments.of(TestMockData.stats, 200),
            Arguments.of(listOf(stat), 100),
        )
    }
}
