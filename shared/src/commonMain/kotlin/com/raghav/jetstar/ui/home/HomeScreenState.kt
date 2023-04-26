package com.raghav.jetstar.ui.home

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.TrendingMedia

@Parcelize
data class HomeScreenState constructor(
    val media: List<TrendingMedia> = emptyList(),
    val isLoading: Boolean = false,
    val isErrorWithMessage: Pair<Exception?, Boolean> = Pair(null, false)
) : Parcelable
