package com.artemissoftware.pokeconnect.core.presentation.models

import android.os.Parcelable
import com.artemissoftware.pokeconnect.core.ui.text.UiText
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorData(
    val message: UiText,
    val onClick: (() -> Unit)? = null,
    val buttonText: UiText = UiText.DynamicString(""),
): Parcelable
