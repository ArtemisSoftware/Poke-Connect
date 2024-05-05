package com.artemissoftware.pokeconnect.features.detail.composables

internal object TestTags {

    const val TYPES_ROW = "types_row"
    const val TYPE_CHIP = "type_chip"

    fun typeChipTag(description: String) = TYPE_CHIP + description
}