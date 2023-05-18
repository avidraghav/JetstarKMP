package com.raghav.jetstar.data.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
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

fun MovieResponseDto.toDomain() =
    results?.map {
        it?.toDomain()
    } ?: emptyList()
