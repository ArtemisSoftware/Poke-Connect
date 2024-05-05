package com.artemissoftware.pokeconnect.features.detail.composables

internal object TestTags {

    const val TYPES_ROW = "types_row"
    private const val TYPE_CHIP = "type_chip"
    fun typeChipTag(description: String) = TYPE_CHIP + description

    const val ABOUT_PAGE = "about_page"
    const val ABOUT_PAGE_POKEMON_DESCRIPTION = "about_page_pokemon_description"
    const val ABOUT_PAGE_POKEMON_HEIGHT = "about_page_pokemon_height"
    const val ABOUT_PAGE_POKEMON_WEIGHT = "about_page_pokemon_weight"
    const val ABOUT_PAGE_POKEMON_ABILITIES = "about_page_pokemon_abilities"

    const val STATS_LIST = "stats_list"
    private const val STATS = "stat"
    private const val STAT_TITLE = "stat_title"
    private const val STAT_VALUE = "stat_value"
    private const val STAT_PROGRESS = "stat_progress"
    fun statTag(description: String) = STATS + description
    fun statTitleTag(description: String) = STAT_TITLE + description
    fun statValueTag(description: String) = STAT_VALUE + description
    fun statProgressTag(description: String) = STAT_PROGRESS + description

    const val DISPLAY_COLUMN = "display_column"
    const val DISPLAY_POKEMON_ID = "display_pokemon_id"
    const val DISPLAY_POKEMON_IMAGE = "display_pokemon_image"
    const val DISPLAY_POKEMON_TYPE = "display_pokemon_type"
}