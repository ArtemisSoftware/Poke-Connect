package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class AbilityDto(
    @Json(name = "ability")
    val ability: UrlNameDto = UrlNameDto(),
    @Json(name = "is_hidden")
    val isHidden: Boolean = false,
    @Json(name = "slot")
    val slot: Int = 0
)