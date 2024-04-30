package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class YellowDto(
    @field:Json(name = "back_default")
    val backDefault: String? = null,
    @field:Json(name = "back_gray")
    val backGray: String? = null,
    @field:Json(name = "back_transparent")
    val backTransparent: String? = null,
    @field:Json(name = "front_default")
    val frontDefault: String? = null,
    @field:Json(name = "front_gray")
    val frontGray: String? = null,
    @field:Json(name = "front_transparent")
    val frontTransparent: String? = null,
)