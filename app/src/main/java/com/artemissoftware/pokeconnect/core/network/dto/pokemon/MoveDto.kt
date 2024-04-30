package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class MoveDto(
    @field:Json(name = "move")
    val move: UrlNameDto = UrlNameDto(),
    @field:Json(name = "version_group_details")
    val versionGroupDetails: List<VersionGroupDetailDto> = listOf()
)