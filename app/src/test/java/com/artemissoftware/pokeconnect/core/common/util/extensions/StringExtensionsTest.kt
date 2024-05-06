package com.artemissoftware.pokeconnect.core.common.util.extensions

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StringExtensionsTest {

    @ParameterizedTest
    @MethodSource("numberFormats")
    fun `Given word should return the word with first char in uppercase`(input: String, expected: String) {
        assertThat(input.upperCaseFirstChar()).isEqualTo(expected)
    }

    private companion object {
        @JvmStatic
        fun numberFormats() = listOf(
            Arguments.of("bulbasur", "Bulbasur"),
            Arguments.of("charmander", "Charmander"),
            Arguments.of("Pikachu", "Pikachu"),
        )
    }
}