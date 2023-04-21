package com.raghav.jetstar.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.jetstar.domain.entity.Movie
import com.raghav.jetstar.router.AppNavigator
import com.raghav.jetstar.router.Router
import com.raghav.jetstar.router.rememberViewModel
import com.raghav.jetstar.ui.HomeState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    router: Router<AppNavigator>,
    modifier: Modifier = Modifier,
    onMovieSelected: (Movie) -> Unit = {}
) {
    val viewModel: HomeScreenVM =
        rememberViewModel(HomeScreenVM::class) { savedState -> HomeScreenVM(savedState) }

    // collectAsStateWithLifecycle() is an Android specific extension
    // hence not available here
    val state = viewModel.state.collectAsState()

    HomeContent(
        state = state.value,
        onMovieSelected = onMovieSelected,
        onRetryClick = {
            viewModel.getTopRatedMovies()
        }
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onMovieSelected: (Movie) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    if (state.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).size(24.dp)
            )
        }
    } else if (state.isErrorWithMessage.second) {
        ErrorScreen(onRetryClick = onRetryClick)
    } else {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Trending Movies",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    backgroundColor = Color.White,
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                )
            },
            content = {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(state.movies.size) { index ->
                        val item = state.movies[index]
                        Column(
                            modifier = Modifier.clickable {
                                onMovieSelected(item)
                            }
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(32.dp),
                                text = "${item.id} - ${item.overview}"
                            )
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ErrorScreen(onRetryClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Something went wrong. Please try again!",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium
        )
        Button(onClick = {
            onRetryClick()
        }, modifier = Modifier.padding(top = 16.dp).wrapContentSize()) {
            Text(
                text = "Retry",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.wrapContentSize(),
                fontWeight = FontWeight.Medium
            )
        }
    }
}
