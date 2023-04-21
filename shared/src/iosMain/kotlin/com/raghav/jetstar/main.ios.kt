package com.raghav.jetstar // ktlint-disable filename

import androidx.compose.ui.window.ComposeUIViewController // ktlint-disable filename
import com.raghav.jetstar.di.initKoin
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController { App() }
}
