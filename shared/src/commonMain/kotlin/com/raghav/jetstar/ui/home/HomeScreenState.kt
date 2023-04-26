package com.raghav.jetstar.ui.home

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.TrendingMedia

@Parcelize
data class HomeScreenState constructor(
    val trendingMovies: List<TrendingMedia> = emptyList(),
    val popularMovies: List<TrendingMedia> = emptyList(),
    val topRatedMovies: List<TrendingMedia> = emptyList(),
    val nowPlayingMovies: List<TrendingMedia> = emptyList(),
    val isLoading: Boolean = false,
    val errorLoadingTrending: Exception? = null,
    val errorLoadingPopular: Exception? = null,
    val errorLoadingTopRated: Exception? = null,
    val errorLoadingNowPlaying: Exception? = null
) : Parcelable
