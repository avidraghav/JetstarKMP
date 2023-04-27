package com.raghav.jetstar.ui // ktlint-disable filename

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.raghav.jetstar.di.initKoin
import com.raghav.jetstar.router.LocalComponentContext
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        val lifecycle = LifecycleRegistry()
        val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

        CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
            App()
        }
    }
}
