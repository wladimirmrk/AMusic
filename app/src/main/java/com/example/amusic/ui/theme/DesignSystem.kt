package com.example.amusic.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class AMusicColorScheme(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val onBackground: Color,
    val surfaceVariant: Color,
    val onSurfaceVariantDisabled: Color,
)

data class AMusicTypography(
    val headline: TextStyle,
    val title1: TextStyle,
    val title2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body2Medium: TextStyle,
    val caption: TextStyle,
)

val LocalAMusicColorScheme = staticCompositionLocalOf {
    AMusicColorScheme(
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        surfaceVariant = Color.Unspecified,
        onSurfaceVariantDisabled = Color.Unspecified,
    )
}

val LocalAMusicTypography = staticCompositionLocalOf {
    AMusicTypography(
        headline = TextStyle.Default,
        title1 = TextStyle.Default,
        title2 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default,
        body2Medium = TextStyle.Default,
        caption = TextStyle.Default,
    )
}