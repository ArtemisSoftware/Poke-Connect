package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class TypeDto(
    @field:Json(name = "slot")
    val slot: Int = 0,
    @field:Json(name = "type")
    val type: UrlNameDto = UrlNameDto()
)