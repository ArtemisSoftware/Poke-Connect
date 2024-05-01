package com.artemissoftware.pokeconnect.core.ui.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.designsystem.LightGray
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.dimension
import com.artemissoftware.pokeconnect.core.designsystem.shape

@Composable
fun CircularIcon(
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: DpSize = MaterialTheme.dimension.iconSize,
    iconContentSize: DpSize = MaterialTheme.dimension.iconContentSize,
    background: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    tint: Color = Color.Unspecified,
) {
    Box(
        modifier = modifier
            .size(iconSize)
            .clip(MaterialTheme.shape.circular)
            .background(background)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = tint,
            modifier = Modifier.size(iconContentSize),
        )
    }
}

@ThemePreviews
@Composable
private fun CircularIconPreview() {
    PokeConnectTheme {
        CircularIcon(
            icon = R.drawable.ic_launcher_foreground,
            onClick = {},
        )
    }
}
