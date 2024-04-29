package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class PokedexEntryDto(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)