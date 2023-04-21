package com.raghav.jetstar.data.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.Movie
import com.raghav.jetstar.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MovieDto(
    val id: Long,
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backDropPath: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("original_language")
    val originalLanguage: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    @SerialName("release_date")
    val releaseDate: String,
    val video: Boolean,
    @SerialName("vote_average")
    val avgVote: Double,
    @SerialName("vote_count")
    val voteCount: Int
) : Parcelable

fun MovieDto.toDomain() = Movie(
    id = id,
    isAdult = adult,
    backDropUrl = Constants.IMAGE_BASE_URL_W500 + backDropPath,
    posterUrl = Constants.IMAGE_BASE_URL_W500 + posterPath,
    genreIds = genreIds,
    language = originalLanguage,
    title = title,
    overview = overview,
    popularity = popularity,
    releaseDate = releaseDate,
    isVideoAvailable = video,
    avgVote = avgVote,
    voteCount = voteCount
)
