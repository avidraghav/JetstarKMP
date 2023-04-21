package com.raghav.jetstar.ui

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.Movie

@Parcelize
data class HomeState constructor(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isErrorWithMessage: Pair<Exception?, Boolean> = Pair(null, false)
) : Parcelable {
    companion object {
        val initialState: HomeState
            get() = HomeState()
    }
}
