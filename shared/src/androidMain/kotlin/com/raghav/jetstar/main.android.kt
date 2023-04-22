package com.raghav.jetstar // ktlint-disable filename

import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView() = App()
