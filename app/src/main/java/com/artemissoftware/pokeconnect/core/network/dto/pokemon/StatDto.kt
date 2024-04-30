package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class StatDto(
    @field:Json(name = "base_stat")
    val baseStat: Int = 0,
    @field:Json(name = "effort")
    val effort: Int = 0,
    @field:Json(name = "stat")
    val stat: UrlNameDto = UrlNameDto()
)