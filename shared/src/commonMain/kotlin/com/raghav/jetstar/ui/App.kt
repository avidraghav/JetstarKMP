package com.raghav.jetstar.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.push
import com.moriatsushi.insetsx.rememberWindowInsetsController
import com.raghav.jetstar.router.AppNavigator
import com.raghav.jetstar.router.RoutedContent
import com.raghav.jetstar.router.Router
import com.raghav.jetstar.router.rememberRouter
import com.raghav.jetstar.ui.home.HomeScreen
import com.raghav.jetstar.ui.overview.MovieOverViewScreen

@Composable
fun App() {
    val windowInsetsController = rememberWindowInsetsController()
    LaunchedEffect(Unit) {
        windowInsetsController?.apply {
            setStatusBarContentColor(dark = false)
            setNavigationBarsContentColor(dark = false)
        }
    }

    JetstarTheme {
        Surface(color = MaterialTheme.colors.surface) {
            val router: Router<AppNavigator> =
                rememberRouter(AppNavigator::class, listOf(AppNavigator.HomeScreen))
            RoutedContent(
                router = router,
                animation = stackAnimation(slide())
            ) { screen ->
                when (screen) {
                    is AppNavigator.MovieOverview -> {
                        MovieOverViewScreen(
                            movie = screen.movie,
                            router = router
                        )
                    }
                    AppNavigator.HomeScreen -> {
                        HomeScreen(
                            onMediaSelected = {
                                router.push(AppNavigator.MovieOverview(it))
                            },
                            router = router
                        )
                    }
                }
            }
        }
    }
}

expect fun getPlatformName(): String
