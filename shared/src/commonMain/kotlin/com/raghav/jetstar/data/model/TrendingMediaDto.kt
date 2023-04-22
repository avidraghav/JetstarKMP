package com.raghav.jetstar.data.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.TrendingMedia
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TrendingMediaDto(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("first_air_date")
    val firstAirDate: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int?>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("media_type")
    val mediaType: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("origin_country")
    val originCountry: List<String?>?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_name")
    val originalName: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
) : Parcelable

fun TrendingMediaDto.toDomain(): TrendingMedia = TrendingMedia(
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    id = id,
    mediaType = mediaType,
    name = name,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalName = originalName,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
