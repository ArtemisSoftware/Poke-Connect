package com.artemissoftware.pokeconnect.core.presentation.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import com.artemissoftware.pokeconnect.core.ui.loading.LoadingProgress
import com.artemissoftware.pokeconnect.core.ui.placeholder.PlaceHolderContent

@Composable
fun PCScaffold(
    content: @Composable () -> Unit,
    isLoading: Boolean = false,
    error: ErrorData? = null,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        content.invoke()

        LoadingProgress(isLoading = isLoading)

        error?.let {
            PlaceHolderContent(
                message = it.message.asString(),
                onClick = it.onClick,
                buttonText = it.buttonText.asString(),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NNSkeletonPreview() {
    PokeConnectTheme {
        val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
        PCScaffold(
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(count = 10) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(colors[it % colors.size]),
                        )
                    }
                }
            },
        )
    }

}