package com.raghav.jetstar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.ui.spacing
import com.raghav.jetstar.util.AsyncImageLoader

@Composable
fun MovieItem(
    item: Movie,
    modifier: Modifier = Modifier,
    isShapeSquare: Boolean = true,
    onItemSelected: (Movie) -> Unit
) {
    Box(
        modifier.then(
            if (isShapeSquare) {
                Modifier.size(160.dp)
            } else {
                Modifier.width(240.dp).height(160.dp)
            }
        )
    ) {
        Card(
            Modifier.padding(end = MaterialTheme.spacing.small).clickable {
                onItemSelected(item)
            }
        ) {
            Column {
                item.backdropPath?.let {
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
            }
        }
        Text(
            item.title.orEmpty(),
            modifier = Modifier.align(Alignment.BottomCenter),
            fontWeight = FontWeight.Bold
        )
    }
}
