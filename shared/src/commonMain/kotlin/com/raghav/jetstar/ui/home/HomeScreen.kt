@file:OptIn(ExperimentalSoftwareKeyboardApi::class)

package com.raghav.jetstar.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.router.AppNavigator
import com.raghav.jetstar.router.Router
import com.raghav.jetstar.router.rememberViewModel
import com.raghav.jetstar.ui.components.ErrorScreen
import com.raghav.jetstar.ui.components.MovieCarousel
import com.raghav.jetstar.ui.components.TopActionBar
import com.raghav.jetstar.ui.spacing

@Composable
fun HomeScreen(
    router: Router<AppNavigator>,
    modifier: Modifier = Modifier,
    onMediaSelected: (Movie) -> Unit = {}
) {
    val viewModel: HomeScreenVM =
        rememberViewModel(HomeScreenVM::class) { savedState -> HomeScreenVM(savedState) }

    // collectAsStateWithLifecycle() is an Android specific extension
    // hence not available here
    val state = viewModel.state.collectAsState()

    HomeContent(
        state = state.value,
        onMediaSelected = onMediaSelected,
        onRetryClick = {
            viewModel.fetchHomeScreenData()
        },
        modifier = modifier
    )
}

@Composable
fun HomeContent(
    state: HomeScreenState,
    modifier: Modifier = Modifier,
    onMediaSelected: (Movie) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    state.apply {
        if (isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center).size(24.dp)
                )
            }
        } else if (
            errorLoadingTrending != null &&
            errorLoadingPopular != null &&
            errorLoadingTopRated != null &&
            errorLoadingNowPlaying != null
        ) {
            ErrorScreen(
                errorLoadingTrending.message.toString(),
                errorLoadingPopular.message.toString(),
                errorLoadingTopRated.message.toString(),
                errorLoadingNowPlaying.message.toString()
            ) {
                onRetryClick()
            }
        } else {
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopActionBar(
                        title = "Jetstar",
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.windowInsetsPadding(
                            WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)
                        )
                    )
                },
                content = {
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = modifier.verticalScroll(scrollState).windowInsetsPadding(
                            WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                        )
                    ) {
                        if (trendingMovies.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            MovieCarousel(
                                title = "Trending Movies",
                                media = trendingMovies
                            ) { media ->
                                onMediaSelected(media)
                            }
                        }
                        if (popularMovies.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            MovieCarousel(
                                title = "Popular Movies",
                                media = popularMovies,
                                isItemShapeSquare = false
                            ) { media ->
                                onMediaSelected(media)
                            }
                        }
                        if (topRatedMovies.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            MovieCarousel(
                                title = "Top Rated Movies",
                                media = topRatedMovies
                            ) { media ->
                                onMediaSelected(media)
                            }
                        }
                        if (nowPlayingMovies.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            MovieCarousel(
                                title = "Now Playing Movies",
                                media = nowPlayingMovies,
                                isItemShapeSquare = false
                            ) { media ->
                                onMediaSelected(media)
                            }
                        }
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
