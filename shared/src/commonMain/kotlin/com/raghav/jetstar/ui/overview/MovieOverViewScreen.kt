package com.raghav.jetstar.ui.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.arkivanov.decompose.router.stack.pop
import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.router.AppNavigator
import com.raghav.jetstar.router.Router
import com.raghav.jetstar.ui.components.TopActionBar
import com.raghav.jetstar.ui.spacing
import com.raghav.jetstar.util.AsyncImageLoader

@Composable
fun MovieOverViewScreen(
    movie: Movie,
    router: Router<AppNavigator>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopActionBar(
                title = "Movie Overview",
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                },
                onNavigationButtonClicked = { router.pop() }
            )
        },
        content = {
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                CardOverViewContent(movie)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CardOverViewContent(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        movie.apply {
            backdropPath?.let {
                AsyncImageLoader(
                    imageUrl = it,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    loadingPlaceHolder = {},
                    errorPlaceHolder = {},
                    alignment = Alignment.Center,
                    alpha = 1.0f,
                    coloFilter = null,
                    filterQuality = FilterQuality.Medium
                )
            }
            title?.let {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    Text("Title")
                    Text(it, fontWeight = FontWeight.Bold)
                }
            }
            releaseDate?.let {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    Text("Release Date")
                    Text(it, fontWeight = FontWeight.Bold)
                }
            }
            overview?.let {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    Text("OverView")
                    Text(it, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
