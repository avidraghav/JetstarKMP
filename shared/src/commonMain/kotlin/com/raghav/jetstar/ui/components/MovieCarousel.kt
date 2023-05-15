package com.raghav.jetstar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.ui.spacing

@Composable
fun MovieCarousel(
    title: String,
    media: List<Movie>,
    modifier: Modifier = Modifier,
    isItemShapeSquare: Boolean = true,
    onMediaSelected: (Movie) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = title,
            Modifier.padding(start = MaterialTheme.spacing.medium),
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            Modifier.padding(start = MaterialTheme.spacing.medium)
        ) {
            items(items = media) {
                MovieItem(
                    item = it,
                    isShapeSquare = isItemShapeSquare,
                    modifier = Modifier.fillParentMaxWidth(0.32f)
                ) { item ->
                    onMediaSelected(item)
                }
            }
        }
    }
}
