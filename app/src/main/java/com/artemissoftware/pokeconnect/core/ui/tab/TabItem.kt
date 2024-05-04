package com.artemissoftware.pokeconnect.core.ui.tab

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class TabItem(
    @StringRes val title: Int,
): Parcelable
