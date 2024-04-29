package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class PokedexNumberDto(
    @field:Json(name = "entry_number")
    val entryNumber: Int = 0,
    @field:Json(name = "pokedex")
    val pokedex: PokedexEntryDto = PokedexEntryDto()
)