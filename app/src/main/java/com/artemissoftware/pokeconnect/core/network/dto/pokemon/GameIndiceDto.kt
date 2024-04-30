package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class GameIndiceDto(
    @Json(name = "game_index")
    val gameIndex: Int,
    @Json(name = "version")
    val version: Version = Version()
)