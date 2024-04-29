package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class PalParkEncounterDto(
    @field:Json(name = "area")
    val area: AreaDto = AreaDto(),
    @field:Json(name = "base_score")
    val baseScore: Int = 0,
    @field:Json(name = "rate")
    val rate: Int = 0
)