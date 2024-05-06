package com.artemissoftware.pokeconnect.features.detail

import android.os.Parcelable
import com.artemissoftware.pokeconnect.core.models.pokemon.Pokemon
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import com.artemissoftware.pokeconnect.core.ui.tab.TabItem
import com.artemissoftware.pokeconnect.features.detail.models.DetailTab
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailState(
    val isLoading: Boolean = false,
    val tabs: List<TabItem> = DetailTab.getTabs(),
    val selectedTabIndex: Int = 0,
    val pokemon: Pokemon? = null,
    val error: ErrorData? = null,
): Parcelable
