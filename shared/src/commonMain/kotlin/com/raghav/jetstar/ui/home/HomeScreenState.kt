package com.raghav.jetstar.ui.home

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.Movie

@Parcelize
data class HomeScreenState constructor(
    val trendingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val topRatedMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorLoadingTrending: Exception? = null,
    val errorLoadingPopular: Exception? = null,
    val errorLoadingTopRated: Exception? = null,
    val errorLoadingNowPlaying: Exception? = null
) : Parcelable
