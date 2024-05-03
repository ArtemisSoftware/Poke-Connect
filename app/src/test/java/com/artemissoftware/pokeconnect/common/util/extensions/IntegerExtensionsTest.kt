package com.artemissoftware.pokeconnect.common.util.extensions

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pokeconnect.core.common.util.extensions.toFormattedNumber
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class IntegerExtensionsTest {

    @ParameterizedTest
    @MethodSource("numberFormats")
    fun `Given number should return the number formatted`(input: Int, expected: String) {
        assertThat(input.toFormattedNumber()).isEqualTo(expected)
    }

    private companion object {
        @JvmStatic
        fun numberFormats() = listOf(
            Arguments.of(1, "#001"),
            Arguments.of(12, "#012"),
            Arguments.of(123, "#123"),
            Arguments.of(1234, "#1234"),
        )
    }
}