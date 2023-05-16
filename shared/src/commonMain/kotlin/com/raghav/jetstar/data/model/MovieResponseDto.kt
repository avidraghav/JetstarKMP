package com.raghav.jetstar.data.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.MovieResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MovieResponseDto(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<MovieDto?>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
) : Parcelable

fun MovieResponseDto.toDomain2() =
    results?.map {
        it?.toDomain()
    } ?: emptyList()

fun MovieResponseDto.toDomain(): MovieResponse =
    MovieResponse(
        page = page,
        results = results?.map {
            it?.toDomain()
        } ?: emptyList(),
        totalPages = totalPages,
        totalResults = totalResults
    )
