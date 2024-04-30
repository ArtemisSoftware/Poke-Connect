package com.artemissoftware.pokeconnect.core.network.dto


import com.artemissoftware.pokeconnect.core.network.dto.pokemon.AbilityDto
import com.artemissoftware.pokeconnect.core.network.dto.pokemon.MoveDto
import com.squareup.moshi.Json

data class PokemonDto(
    @field:Json(name = "abilities")
    val abilities: List<AbilityDto> = emptyList(),
    @field:Json(name = "base_experience")
    val baseExperience: Int = 0,
//    @field:Json(name = "cries")
//    val cries: CriesDto = CriesDto(),
//    @field:Json(name = "forms")
//    val forms: List<UrlNameDto> = emptyList(),
//    @field:Json(name = "game_indices")
//    val gameIndices: List<GameIndiceDto> = emptyList(),
    @field:Json(name = "height")
    val height: Int = 0,
//    @field:Json(name = "held_items")
//    val heldItems: List<Any> = listOf(), // TODO: encontrar exemplo
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "is_default")
    val isDefault: Boolean = false,
    @field:Json(name = "location_area_encounters")
    val locationAreaEncounters: String,
    @field:Json(name = "moves")
    val moves: List<MoveDto> = emptyList(),
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "order")
    val order: Int = 0,
//    @field:Json(name = "past_abilities")
//    val pastAbilities: List<Any> = listOf(), // TODO: encontrar exemplo
//    @field:Json(name = "past_types")
//    val pastTypes: List<Any> = listOf(), // TODO: encontrar exemplo
//    @field:Json(name = "species")
//    val species: UrlNameDto = UrlNameDto(),
//    @field:Json(name = "sprites")
//    val sprites: SpritesDto = SpritesDto(),
//    @Json(name = "stats")
//    val stats: List<StatDto> = emptyList(),
//    @Json(name = "types")
//    val types: List<TypeDto> = emptyList(),
//    @field:Json(name = "weight")
    val weight: Int = 0
)