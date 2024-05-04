package com.artemissoftware.pokeconnect.core.ui.navbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelDestination(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
)
