package com.artemissoftware.pokeconnect.core.presentation.models

import com.artemissoftware.pokeconnect.core.ui.text.UiText

data class ErrorData(
    val message: UiText,
    val onClick: (() -> Unit)? = null,
    val buttonText: UiText = UiText.DynamicString(""),
)
