package com.artemissoftware.pokeconnect.core.data.mappers

import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.network.PokeApi
import com.artemissoftware.pokeconnect.core.network.dto.pokedex.PokedexEntryDto

internal fun PokedexEntryDto.toPokedexEntry(): PokedexEntry {
    val id = url.toPokemonId()
    return PokedexEntry(
        id = id.toInt(),
        name = name,
        imageUrl = id.getImageUrl()
    )
}

private fun String.toPokemonId(): String {
    return if(this.endsWith("/")) {
        this.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        this.takeLastWhile { it.isDigit() }
    }
}

private fun String.getImageUrl(): String {
    return PokeApi.DEFAULT_IMAGE_URL + this + PokeApi.DEFAULT_IMAGE_URL_EXTENSION
}