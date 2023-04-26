package com.raghav.jetstar.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.push
import com.raghav.jetstar.router.AppNavigator
import com.raghav.jetstar.router.RoutedContent
import com.raghav.jetstar.router.Router
import com.raghav.jetstar.router.rememberRouter
import com.raghav.jetstar.ui.home.HomeScreen

@Composable
fun App() {
    JetstarTheme {
        Surface(color = MaterialTheme.colors.surface) {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val router: Router<AppNavigator> =
                rememberRouter(AppNavigator::class, listOf(AppNavigator.HomeScreen))
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopActionBar()
                }
            ) { innerPadding ->
                RoutedContent(
                    router = router,
                    animation = stackAnimation(slide())
                ) { screen ->
                    when (screen) {
                        is AppNavigator.MediaDetail -> {
                        }
                        AppNavigator.HomeScreen -> {
                            HomeScreen(
                                onMediaSelected = {
                                    router.push(AppNavigator.MediaDetail(it))
                                },
                                router = router
                            )
                        }
                    }
                }
            }
        }
    }
}

expect fun getPlatformName(): String
