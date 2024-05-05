package com.artemissoftware.pokeconnect.features.detail

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


    const val DETAIL_PANEL = "detail_panel"
    const val DETAIL_CONTENT = "detail_content"
    const val DETAIL_TOP_BAR = "detail_top_bar"
    const val DETAIL_BACK_BUTTON = "detail_back_button"
    const val FAVORITE_BUTTON = "favorite_button"

    const val DETAIL_DISPLAY = "detail_display"
    private const val DETAIL_PAGE_TAB = "detail_page"
    fun getPageTabTestTag(description: String) = DETAIL_PAGE_TAB + description
    const val DETAIL_PAGER = "detail_pager"
}