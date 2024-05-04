package com.artemissoftware.pokeconnect.features.detail.composables.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.common.util.extensions.upperCaseFirstChar
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.dimension
import com.artemissoftware.pokeconnect.core.designsystem.shape
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.models.Stat
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor
import com.artemissoftware.pokeconnect.core.ui.placeholder.PlaceHolderContent
import com.artemissoftware.pokeconnect.features.PreviewData

@Composable
internal fun StatsPage(
    stats: List<Stat>,
    modifier: Modifier = Modifier,
    paletteColor: PaletteColor = PaletteColor(),
) {
    if(stats.isEmpty()){
        PlaceHolderContent(
            message = stringResource(id = R.string.no_stats_available),
        )
    }else {
        val statMaxValue by remember {
            mutableIntStateOf(
                roundToNearestIncrement(stats.map { it.value }.max())
            )
        }

        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5)
        )  {
            items(stats){ stat ->
                Stat(
                    title = stat.abbreviation,
                    value = stat.value,
                    maxValue = statMaxValue.toFloat(),
                    progressColor = paletteColor.background
                )
            }
        }
    }
}

@Composable
private fun Stat(
    title: String,
    value: Int,
    maxValue: Float,
    progressColor: Color,
    modifier: Modifier = Modifier,
) {

    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val animatedProgress = animateFloatAsState(
        targetValue = if(animationPlayed) {
            value / maxValue
        } else 0f,
        animationSpec = tween(
            1000,
            0
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.width(MaterialTheme.dimension.statMaxWidth),
            text = title.upperCaseFirstChar(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
        )

        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodySmall,
        )

        LinearProgressIndicator(
            progress = { animatedProgress.value },
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimension.progressBarHeightSmall)
                .clip(shape = MaterialTheme.shape.circular),
            color = progressColor,
            trackColor = progressColor.copy(alpha = 0.1F),
        )
    }
}

fun roundToNearestIncrement(number: Int, increment: Int = 100): Int {
    // Calculate the closest rounded value
    val remainder = number % increment
    return if (remainder >= increment / 2) {
        // If the remainder is at least half of the increment, round up
        number + (increment - remainder)
    } else {
        // Otherwise, round down
        number - remainder
    }
}

@ThemePreviews
@Composable
private fun StatsPagePreview() {
    PokeConnectTheme {
        StatsPage(
            stats = PreviewData.pokemon.stats,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@ThemePreviews
@Composable
private fun StatsPage_empty_Preview() {
    PokeConnectTheme {
        StatsPage(
            stats = emptyList(),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}