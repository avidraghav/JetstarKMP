package com.raghav.jetstar.router

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.TrendingMedia

@Parcelize
sealed class AppNavigator : Parcelable {
    object HomeScreen : AppNavigator()
    data class MediaDetail(val detail: TrendingMedia) : AppNavigator()
}
