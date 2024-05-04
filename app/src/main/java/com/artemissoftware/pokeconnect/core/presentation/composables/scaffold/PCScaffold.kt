package com.artemissoftware.pokeconnect.core.presentation.composables.scaffold

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.presentation.models.ErrorData
import com.artemissoftware.pokeconnect.core.ui.loading.LoadingProgress
import com.artemissoftware.pokeconnect.core.ui.placeholder.PlaceHolderContent
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PCScaffold(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    error: ErrorData? = null,
    bottomBar: @Composable() ((Modifier) -> Unit?)? = null,
) {
    val bottomBarHeight = 124.0.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

// connection to the nested scroll system and listen to the scroll
// happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    val collapsedModifier = Modifier
        .height(bottomBarHeight)
        .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.floatValue.roundToInt()) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            modifier = Modifier
                .then(modifier)
                .nestedScroll(nestedScrollConnection),
            bottomBar = {
                bottomBar?.invoke(collapsedModifier)
            },
            content = {  innerPadding ->

                val topPadding =  0.dp

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = topPadding)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        content.invoke()
                    }
                }
            },
        )

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

@Composable
fun PCScaffoldDouble(
    contentLeft: @Composable () -> Unit,
    contentRight: @Composable () -> Unit,
    isLoading: Boolean = false,
    error: ErrorData? = null,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(modifier = Modifier.weight(0.5F)) {
                contentLeft.invoke()
            }
            Box(modifier = Modifier.weight(0.5F)) {
                contentRight.invoke()
            }
        }


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

@Preview
@Composable
private fun PCScaffoldPreview() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PCScaffoldDoublePreview() {
    PokeConnectTheme {
        val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
        PCScaffoldDouble(
            contentRight = {
                   Box(modifier = Modifier
                       .fillMaxSize()
                       .background(Color.DarkGray))
            },
            contentLeft = {
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