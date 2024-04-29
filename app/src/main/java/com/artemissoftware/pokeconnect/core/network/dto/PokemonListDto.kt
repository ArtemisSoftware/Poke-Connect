package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class PokemonListDto(
    @field:Json(name = "count")
    val count: Int = 0,
    @field:Json(name = "next")
    val next: String = "",
    @field:Json(name = "previous")
    val previous: Any? = null,
    @field:Json(name = "results")
    val results: List<PokedexEntryDto> = listOf()
)