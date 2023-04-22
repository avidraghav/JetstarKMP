package com.raghav.jetstar

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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
    MaterialTheme {
        val router: Router<AppNavigator> =
            rememberRouter(AppNavigator::class, listOf(AppNavigator.HomeScreen))
        RoutedContent(
            router = router,
            animation = stackAnimation(slide())
        ) { screen ->
            when (screen) {
                is AppNavigator.MediaDetail -> {
                }
                AppNavigator.HomeScreen -> {
                    HomeScreen(
                        onMovieSelected = {
                            router.push(AppNavigator.MediaDetail(it))
                        },
                        router = router
                    )
                }
            }
        }
    }
}

expect fun getPlatformName(): String
