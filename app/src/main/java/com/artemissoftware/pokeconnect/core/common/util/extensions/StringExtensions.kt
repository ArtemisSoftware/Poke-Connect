package com.artemissoftware.pokeconnect.core.common.util.extensions

fun String.upperCaseFirstChar() = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }