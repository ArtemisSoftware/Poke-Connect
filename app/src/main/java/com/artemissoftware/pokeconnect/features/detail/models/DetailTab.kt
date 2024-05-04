package com.artemissoftware.pokeconnect.features.detail.models

import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.ui.tab.TabItem

internal enum class DetailTab(val index: Int, val tabItem: TabItem) {
    NOT_FOUND(
        index = 0,
        tabItem = TabItem(
            title = R.string.not_found,
        ),
    ),
    ABOUT(
        index = 1,
        tabItem = TabItem(
            title = R.string.about,
        ),
    ),
    STATS(
        index = 2,
        tabItem = TabItem(
            title = R.string.stats,
        ),
    );

    companion object {

        fun getTabs(): List<TabItem> {
            return entries.sortedBy { it.index }.drop(1).map { it.tabItem }
        }

        fun getTabByIndex(index: Int): DetailTab {
            return entries.find { it.index == (index + 1) } ?: NOT_FOUND
        }
    }
}