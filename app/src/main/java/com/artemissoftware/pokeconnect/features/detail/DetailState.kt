package com.artemissoftware.pokeconnect.features.detail

import com.artemissoftware.pokeconnect.core.models.Pokemon
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import com.artemissoftware.pokeconnect.core.ui.tab.TabItem
import com.artemissoftware.pokeconnect.features.detail.models.DetailTab

data class DetailState(
    val isLoading: Boolean = false,
    val tabs: List<TabItem> = DetailTab.getTabs(),
    val selectedTabIndex: Int = 0,
    val pokemon: Pokemon? = null,
    val error: ErrorData? = null,
)
