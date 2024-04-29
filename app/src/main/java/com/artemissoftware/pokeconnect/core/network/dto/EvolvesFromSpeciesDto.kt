package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class EvolvesFromSpeciesDto(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "url")
    val url: String = ""
)