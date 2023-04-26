package com.raghav.jetstar.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun JetstarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors =
        darkColors(
            primary = Blue70,
            secondary = PurpleGrey80,
            surface = Blue10,
            background = Black40,
            onBackground = White100,
            onSurface = White90
        )

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = colors,
            content = content
        )
    }
}
