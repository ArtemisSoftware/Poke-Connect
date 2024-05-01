package com.artemissoftware.pokeconnect.core.common.util.extensions

import java.util.Locale

fun String.upperCaseFirstChar() = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }