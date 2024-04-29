package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class VarietyDto(
    @field:Json(name = "is_default")
    val isDefault: Boolean = false,
    @field:Json(name = "pokemon")
    val pokemon: ResumeDto = ResumeDto()
)