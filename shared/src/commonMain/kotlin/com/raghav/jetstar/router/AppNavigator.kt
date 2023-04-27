package com.raghav.jetstar.router

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.Movie

@Parcelize
sealed class AppNavigator : Parcelable {
    object HomeScreen : AppNavigator()
    data class MovieOverview(val movie: Movie) : AppNavigator()
}
