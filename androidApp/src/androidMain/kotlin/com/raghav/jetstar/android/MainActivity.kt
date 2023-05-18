package com.raghav.jetstar.android

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.raghav.jetstar.router.LocalComponentContext
import com.raghav.jetstar.ui.MainView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        val rootComponentContext: DefaultComponentContext = defaultComponentContext()
        setContent {
            CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
                MainView()
            }
        }
    }
}
