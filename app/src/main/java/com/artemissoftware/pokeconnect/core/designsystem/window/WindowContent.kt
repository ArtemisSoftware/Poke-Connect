package com.artemissoftware.pokeconnect.core.designsystem.window

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.artemissoftware.pokeconnect.core.designsystem.window

@Composable
fun WindowContent(
    landScapeContent: @Composable () -> Unit,
    portraitContent: @Composable () -> Unit,
) {
    if(MaterialTheme.window.isLandScape()){
        landScapeContent()
    } else {
        portraitContent()
    }
}