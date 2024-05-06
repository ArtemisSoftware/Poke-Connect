package com.artemissoftware.pokeconnect.core.ui.placeholder

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.dimension
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER_PAGE
import com.artemissoftware.pokeconnect.core.ui.TestTags.PLACE_HOLDER__PAGE_TEXT


@Composable
fun PlaceHolderContent(
    message: String,
    @DrawableRes icon: Int = R.drawable.ic_placeholder,
    onClick: (() -> Unit)? = null,
    buttonText: String = "",
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.8f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "",
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    Column(
        modifier = Modifier
            .testTag(PLACE_HOLDER_PAGE)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(MaterialTheme.dimension.iconSizeBig)
                .alpha(alphaAnimation),
        )
        Text(
            modifier = Modifier
                .testTag(PLACE_HOLDER__PAGE_TEXT)
                .padding(MaterialTheme.spacing.spacing1_5)
                .alpha(alphaAnimation),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
        )
        onClick?.let {
            Button(onClick = it) {
                Text(text = buttonText)
            }
        }
    }
}

@Composable
fun PlaceHolderNotice(
    message: String,
    @DrawableRes icon: Int = R.drawable.ic_placeholder,
    onClick: (() -> Unit)? = null,
    buttonText: String = "",
) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.8f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "",
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    Row(
        modifier = Modifier
            .testTag(PLACE_HOLDER_PAGE)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {

        Row(modifier = Modifier.weight(0.7F)) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(MaterialTheme.dimension.iconSize)
                    .alpha(alphaAnimation),
            )
            Text(
                modifier = Modifier
                    .testTag(PLACE_HOLDER__PAGE_TEXT)
                    .padding(MaterialTheme.spacing.spacing1_5)
                    .alpha(alphaAnimation),
                text = message,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        onClick?.let {
            Button(onClick = it) {
                Text(text = buttonText)
            }
        }
    }
}

@ThemePreviews
@Composable
private fun PlaceHolderContentPreview() {
    PokeConnectTheme {
        PlaceHolderContent(
            message = "Internet Unavailable.",
        )
    }
}

@ThemePreviews
@Composable
private fun PlaceHolderNoticePreview() {
    PokeConnectTheme {
        PlaceHolderNotice(
            message = "Internet Unavailable.",
        )
    }
}
