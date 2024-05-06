package com.artemissoftware.pokeconnect.core.models.pokemon.mappers

import com.artemissoftware.pokeconnect.core.models.pokemon.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.pokemon.Pokemon

internal fun Pokemon.toPokedexEntry(): PokedexEntry {
    return PokedexEntry(id = id, name = name, imageUrl = imageUrl)
}