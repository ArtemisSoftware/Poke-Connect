package com.artemissoftware.pokeconnect.core.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.artemissoftware.pokeconnect.R

private val poppinsFamily: FontFamily = FontFamily(
    listOf(
        //Font(R.font.poppins_bold),
        Font(R.font.poppins_regular),
        //Font(R.font.poppins_semibold),
    ),
)

private val undefined = TextStyle()

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = poppinsFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = poppinsFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = poppinsFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = poppinsFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = poppinsFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = poppinsFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = poppinsFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = poppinsFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = poppinsFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = poppinsFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = poppinsFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = poppinsFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = poppinsFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = poppinsFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = poppinsFamily)
)

@ThemePreviews
@Composable
private fun TypographyPreview() {
    with(Typography){
        val typography = mapOf(
            "undefined" to undefined,
            "displayMedium" to displayMedium,
            "displaySmall" to displaySmall,
            "bodyMedium" to bodyMedium,
            "bodySmall" to bodySmall,
            "labelLarge" to labelLarge,
            "labelMedium" to labelMedium,
            "labelSmall" to labelSmall,


)

        PokeConnectTheme {
            Surface {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.spacing0_5),
                ) {
                    items(typography.toList()) { pair ->
                        TypographyDescription(
                            text = pair.first,
                            textStyle = pair.second,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TypographyDescription(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$text - " + textStyle.fontSize,
        style = textStyle,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}