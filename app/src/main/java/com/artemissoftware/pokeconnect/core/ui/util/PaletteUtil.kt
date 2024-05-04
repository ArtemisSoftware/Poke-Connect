package com.artemissoftware.pokeconnect.core.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteColor
import com.artemissoftware.pokeconnect.core.ui.palette.PaletteType

object PaletteUtil {

    suspend fun getPaletteFromImageUrl(context: Context, imageUrl: String): PaletteColor{
        val bitmap = convertImageUrlToBitmap(
            imageUrl = imageUrl,
            context = context
        )
        val paletteColor = if (bitmap != null) {
            val colors = extractColorsFromBitmap(
                bitmap = bitmap
            )
            val backGroundColor = Color(android.graphics.Color.parseColor(colors[PaletteType.VIBRANT]))
            val textColor = Color(android.graphics.Color.parseColor(colors[PaletteType.ON_DARK_VIBRANT]))

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
                color = Palette.from(bitmap).generate().lightVibrantSwatch
            ),
            PaletteType.DARK_VIBRANT to parseColorSwatch(
                color = Palette.from(bitmap).generate().darkVibrantSwatch
            ),
            PaletteType.ON_DARK_VIBRANT to parseBodyColor(
                color = Palette.from(bitmap).generate().lightVibrantSwatch?.bodyTextColor
            )
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