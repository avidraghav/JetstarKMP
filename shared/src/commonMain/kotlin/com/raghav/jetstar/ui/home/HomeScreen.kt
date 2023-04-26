package com.raghav.jetstar.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.jetstar.domain.entity.trending.TrendingMedia
import com.raghav.jetstar.router.AppNavigator
import com.raghav.jetstar.router.Router
import com.raghav.jetstar.router.rememberViewModel
import com.raghav.jetstar.ui.spacing

@OptIn(ExperimentalAnimationApi::class)
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
            viewModel.getTopRatedMovies()
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
    if (state.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).size(24.dp)
            )
        }
    } else if (state.isErrorWithMessage.second) {
        ErrorScreen(state.isErrorWithMessage.first?.message.toString(), onRetryClick = onRetryClick)
    } else {
//        val scaffoldState = rememberScaffoldState()
//        Scaffold(
//            scaffoldState = scaffoldState,
//            content = {
//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(2),
//                    modifier = Modifier.fillMaxSize(),
//                    contentPadding = PaddingValues(8.dp),
//                    horizontalArrangement = Arrangement.spacedBy(4.dp),
//                    verticalArrangement = Arrangement.spacedBy(4.dp)
//                ) {
//                    items(state.media?.size ?: 0) { index ->
//                        val item = state.media?.get(index)
//                        Column(
//                            modifier = Modifier.clickable {
//                                onMovieSelected(item!!)
//                            }
//                        ) {
//                            Text(
//                                modifier = Modifier
//                                    .padding(32.dp),
//                                text = "${item?.id} - ${item?.overview}"
//                            )
//                        }
//                    }
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        )

        TrendingMediaCarousel(title = "Title", media = state.media) { media ->
            onMediaSelected(media)
        }
    }
}

@Composable
fun TrendingMediaCarousel(
    title: String,
    media: List<TrendingMedia>,
    modifier: Modifier = Modifier,
    onMediaSelected: (TrendingMedia) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = title, Modifier.padding(start = MaterialTheme.spacing.medium))
        LazyRow(
            Modifier.padding(start = MaterialTheme.spacing.medium)
        ) {
            items(items = media) {
                TrendingMediaItem(it) { item ->
                    onMediaSelected(item)
                }
            }
        }
    }
}

@Composable
fun TrendingMediaItem(
    item: TrendingMedia,
    modifier: Modifier = Modifier,
    onItemSelected: (TrendingMedia) -> Unit
) {
    Card(
        modifier.size(160.dp).padding(end = MaterialTheme.spacing.small).clickable {
            onItemSelected(item)
        }
    ) {
        Text(item.title.toString())
    }
}

@Composable
fun ErrorScreen(message: String, onRetryClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium
        )
        Button(onClick = {
            onRetryClick()
        }, modifier = Modifier.padding(top = MaterialTheme.spacing.medium).wrapContentSize()) {
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
