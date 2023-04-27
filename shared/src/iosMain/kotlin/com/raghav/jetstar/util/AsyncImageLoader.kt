package com.raghav.jetstar.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale

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
    val imageState by rememberImageState(imageUrl)
    Box(modifier = modifier) {
        when (val state = imageState) {
            ImageState.Error -> errorPlaceHolder()
            ImageState.Loading -> loadingPlaceHolder()
            is ImageState.Success -> {
                Image(
                    bitmap = state.bitmap,
                    contentDescription = contentDescription,
                    modifier = modifier,
                    alignment = alignment,
                    contentScale = contentScale,
                    alpha = alpha,
                    colorFilter = coloFilter,
                    filterQuality = filterQuality
                )
            }
        }
    }
}

@Composable
fun rememberImageState(
    imageUrl: String
) = produceState<ImageState>(initialValue = ImageState.Loading) {
    runCatching {
        ImageRepository.getImageBitmapByUrl(imageUrl)
    }.onSuccess {
        value = ImageState.Success(it)
    }.onFailure {
        value = ImageState.Error
    }
}

sealed class ImageState {
    object Loading : ImageState()
    object Error : ImageState()
    data class Success(val bitmap: ImageBitmap) : ImageState()
}
