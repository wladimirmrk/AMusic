package com.example.amusic.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.amusic.R

private val satoshiFamily = FontFamily(
    Font(R.font.satoshi_regular, FontWeight.Normal),
    Font(R.font.satoshi_medium, FontWeight.Medium),
    Font(R.font.satoshi_bold, FontWeight.Bold)
)

object AMusicTextStyles {
    val headline = createTextStyle(24.sp, FontWeight.Bold)
    val title1 = createTextStyle(24.sp, FontWeight.Bold)
    val title2 = createTextStyle(14.sp, FontWeight.Bold)
    val body1 = createTextStyle(16.sp, FontWeight.Normal)
    val body2 = createTextStyle(12.sp, FontWeight.Normal)
    val body2Medium = createTextStyle(14.sp, FontWeight.Medium)
    val caption = createTextStyle(10.sp, FontWeight.Bold)

    private fun createTextStyle(fontSize: TextUnit, fontWeight: FontWeight): TextStyle {
        return TextStyle(
            fontFamily = satoshiFamily,
            fontWeight = fontWeight,
            fontSize = fontSize,
            letterSpacing = fontSize * 0
        )
    }
}