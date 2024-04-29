package com.artemissoftware.pokeconnect.core.network.dto


import com.squareup.moshi.Json

data class PokemonDto(
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "base_happiness")
    val baseHappiness: Int = 0,
    @field:Json(name = "capture_rate")
    val captureRate: Int = 0,
    @field:Json(name = "color")
    val color: ColorDto = ColorDto(),
    @field:Json(name = "egg_groups")
    val eggGroups: List<EggGroupDto> = emptyList(),
    @field:Json(name = "evolution_chain")
    val evolutionChain: EvolutionChainDto = EvolutionChainDto(),
    @field:Json(name = "evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpeciesDto? = null,
    @field:Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntriesDto> = emptyList(),
    @field:Json(name = "form_descriptions")
    val formDescriptions: List<Any> = listOf(),  //-------------------
    @field:Json(name = "forms_switchable")
    val formsSwitchable: Boolean = false,
    @field:Json(name = "gender_rate")
    val genderRate: Int = 0,
    @field:Json(name = "genera")
    val genera: List<GeneraDto> = emptyList(),
    @field:Json(name = "generation")
    val generation: GenerationDto = GenerationDto(),
    @field:Json(name = "growth_rate")
    val growthRate: GrowthRateDto = GrowthRateDto(),
    @field:Json(name = "habitat")
    val habitat: HabitatDto = HabitatDto(),
    @field:Json(name = "has_gender_differences")
    val hasGenderDifferences: Boolean = false,
    @field:Json(name = "hatch_counter")
    val hatchCounter: Int = 0,
    @field:Json(name = "is_baby")
    val isBaby: Boolean = false,
    @field:Json(name = "is_legendary")
    val isLegendary: Boolean = false,
    @field:Json(name = "is_mythical")
    val isMythical: Boolean = false,
    @field:Json(name = "names")
    val names: List<NameDto> = emptyList(),
    @field:Json(name = "order")
    val order: Int = 0,
    @field:Json(name = "pal_park_encounters")
    val palParkEncounters: List<PalParkEncounterDto> = emptyList(),
    @field:Json(name = "pokedex_numbers")
    val pokedexNumbers: List<PokedexNumberDto> = emptyList(),
    @field:Json(name = "shape")
    val shape: ShapeDto = ShapeDto(),
    @field:Json(name = "varieties")
    val varieties: List<Any> = listOf()
)