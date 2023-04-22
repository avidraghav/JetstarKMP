package com.raghav.jetstar.data.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.raghav.jetstar.domain.entity.trending.TrendingMediaResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TrendingMediaResponseDto(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<TrendingMediaDto?>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
) : Parcelable

fun TrendingMediaResponseDto.toDomain(): TrendingMediaResponse =
    TrendingMediaResponse(
        page = page,
        results = results?.map {
            it?.toDomain()
        } ?: emptyList(),
        totalPages = totalPages,
        totalResults = totalResults
    )
