package com.artemissoftware.pokeconnect.core.network.dto.pokemon


import com.squareup.moshi.Json

data class PokemonDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "weight")
    val weight: Int,
    @field:Json(name = "sprites")
    val sprites: SpritesDto = SpritesDto(),
    @field:Json(name = "abilities")
    val abilities: List<AbilityDto> = emptyList(),
    @field:Json(name = "stats")
    val stats: List<StatDto> = emptyList(),
    @field:Json(name = "types")
    val types: List<TypeDto> = emptyList(),
    @field:Json(name = "cries")
    val cries: CriesDto = CriesDto(),
    @field:Json(name = "base_experience")
    val baseExperience: Int,
    @field:Json(name = "is_default")
    val isDefault: Boolean = false,
    @field:Json(name = "order")
    val order: Int,
    @field:Json(name = "location_area_encounters")
    val locationAreaEncounters: String,
    @field:Json(name = "species")
    val species: UrlNameDto = UrlNameDto(),
    @field:Json(name = "forms")
    val forms: List<UrlNameDto> = emptyList(),
    @field:Json(name = "moves")
    val moves: List<MoveDto> = emptyList(),
)