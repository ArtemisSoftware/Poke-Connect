package com.artemissoftware.pokeconnect.core.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteType
import kotlin.math.max
import kotlin.math.min

object PaletteUtil {

    suspend fun getPaletteFromImageUrl(context: Context, imageUrl: String, isDarkMode: Boolean): PaletteColor{
        val bitmap = convertImageUrlToBitmap(
            imageUrl = imageUrl,
            context = context
        )
        val paletteColor = if (bitmap != null) {
            val colors = extractColorsFromBitmap(
                bitmap = bitmap
            )
            val backGroundColorProposal = if(isDarkMode) Color(android.graphics.Color.parseColor(colors[PaletteType.DARK_VIBRANT])) else Color(android.graphics.Color.parseColor(colors[PaletteType.VIBRANT]))
            val textColor = if(isDarkMode) Color(android.graphics.Color.parseColor(colors[PaletteType.ON_VIBRANT])) else Color(android.graphics.Color.parseColor(colors[PaletteType.ON_VIBRANT]))

            val backGroundColor = if(isColorCloseToBlack(backGroundColorProposal) && isDarkMode) Color(0xFFFFFDD0)  else backGroundColorProposal

            PaletteColor(
                text = textColor,
                background = backGroundColor,
            )
        }
        else {
            PaletteColor()
        }

        return paletteColor
    }

    fun isColorCloseToBlack(color: Color, lightnessThreshold: Float = 0.25f): Boolean {
        // Convert the color to ARGB and extract its components
        val argb = color.toArgb()
        val r = ((argb shr 16) and 0xFF) / 255f
        val g = ((argb shr 8) and 0xFF) / 255f
        val b = (argb and 0xFF) / 255f

        // Calculate the lightness based on the RGB components
        val maxComponent = max(max(r, g), b)
        val minComponent = min(min(r, g), b)
        val lightness = (maxComponent + minComponent) / 2

        // Check if the lightness is below the threshold
        return lightness < lightnessThreshold
    }

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val imageResult = loader.execute(request = request)
        return if (imageResult is SuccessResult) {
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColorsFromBitmap(bitmap: Bitmap): Map<PaletteType, String> {
        return mapOf(
            PaletteType.VIBRANT to parseColorSwatch(
                color = Palette.from(bitmap).generate().dominantSwatch
            ),
            PaletteType.ON_VIBRANT to parseBodyColor(
                color = Palette.from(bitmap).generate().lightVibrantSwatch?.bodyTextColor
            ),
            PaletteType.DARK_VIBRANT to parseColorSwatch(
                color = Palette.from(bitmap).generate().dominantSwatch
            ),
            PaletteType.ON_DARK_VIBRANT to parseBodyColor(
                color = Palette.from(bitmap).generate().lightVibrantSwatch?.bodyTextColor
            ),
        )
    }

    private fun parseColorSwatch(color: Palette.Swatch?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color.rgb)
            return "#$parsedColor"
        } else {
            "#000000"
        }
    }

    private fun parseBodyColor(color: Int?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color)
            "#$parsedColor"
        } else {
            "#FFFFFF"
        }
    }
}