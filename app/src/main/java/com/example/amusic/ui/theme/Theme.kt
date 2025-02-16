package com.example.amusic.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

private val colorScheme = AMusicColorScheme(
    primary = AMusicColors.Blue,
    secondary = AMusicColors.Green,
    secondaryVariant = AMusicColors.Gray,
    background = AMusicColors.Black,
    onBackground = Color.White,
    surfaceVariant = AMusicColors.DarkGray,
    onSurfaceVariantDisabled = AMusicColors.Black,
)

private val typography = AMusicTypography(
    headline = AMusicTextStyles.headline,
    title1 = AMusicTextStyles.title1,
    title2 = AMusicTextStyles.title2,
    body1 = AMusicTextStyles.body1,
    body2 = AMusicTextStyles.body2,
    body2Medium = AMusicTextStyles.body2Medium,
    caption = AMusicTextStyles.caption,
)

@Composable
fun AMusicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAMusicColorScheme provides colorScheme,
        LocalAMusicTypography provides typography,
        content = content
    )
}

object AMusicTheme {
    val colorScheme: AMusicColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAMusicColorScheme.current

    val typography: AMusicTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAMusicTypography.current
}