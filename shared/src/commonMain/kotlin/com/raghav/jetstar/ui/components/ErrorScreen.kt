package com.raghav.jetstar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.raghav.jetstar.ui.spacing

@Composable
fun ErrorScreen(
    errorTrendingMovies: String,
    errorPopularMovies: String,
    errorTopRated: String,
    errorNowPlayingMovies: String,
    onRetryClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error Loading Trending Movies $errorTrendingMovies",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Error Loading Popular Movies $errorPopularMovies",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Error Loading Top Rated Movies $errorTopRated",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Error Loading Now Playing Movies $errorNowPlayingMovies",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            onRetryClick()
        }, modifier = Modifier.padding(top = MaterialTheme.spacing.medium).wrapContentSize()) {
            Text(
                text = "Retry",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}
