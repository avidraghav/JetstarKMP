package com.raghav.jetstar.util

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import coil.compose.SubcomposeAsyncImage

@Composable
actual fun AsyncImageLoader(
    imageUrl: String,
    loadingPlaceHolder: @Composable BoxScope.() -> Unit,
    errorPlaceHolder: @Composable BoxScope.() -> Unit,
    contentDescription: String?,
    modifier: Modifier,
    alignment: Alignment,
    contentScale: ContentScale,
    alpha: Float,
    coloFilter: ColorFilter?,
    filterQuality: FilterQuality
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = coloFilter,
        filterQuality = filterQuality,
        loading = { loadingPlaceHolder() },
        error = { errorPlaceHolder() }
    )
}
