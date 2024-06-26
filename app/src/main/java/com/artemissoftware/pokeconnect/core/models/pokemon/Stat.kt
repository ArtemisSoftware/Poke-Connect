package com.artemissoftware.pokeconnect.core.models.pokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Stat(
    val value: Int,
    val description: String,
    val abbreviation: String,
) : Parcelable
