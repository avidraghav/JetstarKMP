package com.raghav.jetstar.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raghav.jetstar.domain.entity.trending.TrendingMedia
import com.raghav.jetstar.router.AppNavigator
import com.raghav.jetstar.router.Router
import com.raghav.jetstar.router.rememberViewModel
import com.raghav.jetstar.ui.components.ErrorScreen
import com.raghav.jetstar.ui.components.MovieCarousel
import com.raghav.jetstar.ui.spacing

@Composable
fun HomeScreen(
    router: Router<AppNavigator>,
    modifier: Modifier = Modifier,
    onMediaSelected: (TrendingMedia) -> Unit = {}
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
    onMediaSelected: (TrendingMedia) -> Unit = {},
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
            val scrollState = rememberScrollState()
            Column(modifier = modifier.verticalScroll(scrollState)) {
                if (trendingMovies.isNotEmpty()) {
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
            }
        }
    }
}
