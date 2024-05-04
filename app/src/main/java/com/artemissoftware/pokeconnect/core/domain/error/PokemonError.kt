package com.artemissoftware.pokeconnect.core.domain.error

sealed class PokemonError : Error {

    data object SearchWithNoResults : PokemonError()
}