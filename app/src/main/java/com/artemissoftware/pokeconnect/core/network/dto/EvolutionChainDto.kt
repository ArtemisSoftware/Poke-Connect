package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class EvolutionChainDto(
    @field:Json(name = "url")
    val url: String = ""
)