package com.artemissoftware.pokeconnect.features.detail.composables

internal object TestTags {

    const val TYPES_ROW = "types_row"
    const val TYPE_CHIP = "type_chip"

    fun typeChipTag(description: String) = TYPE_CHIP + description

    const val ABOUT_PAGE = "about_page"
    const val ABOUT_PAGE_POKEMON_DESCRIPTION = "about_page_pokemon_description"
    const val ABOUT_PAGE_POKEMON_HEIGHT = "about_page_pokemon_height"
    const val ABOUT_PAGE_POKEMON_WEIGHT = "about_page_pokemon_weight"
    const val ABOUT_PAGE_POKEMON_ABILITIES = "about_page_pokemon_abilities"
}