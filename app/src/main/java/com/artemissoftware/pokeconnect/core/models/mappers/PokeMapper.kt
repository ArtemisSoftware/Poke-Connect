package com.artemissoftware.pokeconnect.core.models.mappers

import com.artemissoftware.pokeconnect.core.models.PokedexEntry
import com.artemissoftware.pokeconnect.core.models.Pokemon

fun Pokemon.toPokedexEntry(): PokedexEntry{
    return PokedexEntry(id = id, name = name, imageUrl = imageUrl)
}