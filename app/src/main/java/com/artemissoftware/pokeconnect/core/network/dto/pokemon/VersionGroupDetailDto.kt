package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class VersionGroupDetailDto(
    @field:Json(name = "level_learned_at")
    val levelLearnedAt: Int = 0,
    @field:Json(name = "move_learn_method")
    val moveLearnMethod: UrlNameDto = UrlNameDto(),
    @field:Json(name = "version_group")
    val versionGroup: UrlNameDto = UrlNameDto()
)