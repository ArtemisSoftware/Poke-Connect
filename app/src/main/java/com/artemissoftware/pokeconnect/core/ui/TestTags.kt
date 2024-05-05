package com.artemissoftware.pokeconnect.core.ui

internal object TestTags {
    const val PLACE_HOLDER_PAGE = "place_holder_page"
    const val PLACE_HOLDER__PAGE_TEXT = "place_holder_page_text"

    private const val NAV_BAR_ITEM = "nav_bar_item"
    fun getNavBarItemTestTag(id: Int) = NAV_BAR_ITEM + id
}