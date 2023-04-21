package com.raghav.jetstar.domain.entity

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val isAdult: Boolean,
    val backDropUrl: String,
    val posterUrl: String,
    val genreIds: List<Int>,
    val genres: List<String> = listOf(),
    val language: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val isVideoAvailable: Boolean,
    val avgVote: Double,
    val voteCount: Int
) : Parcelable

@Parcelize
data class MovieVideo(
    val key: String,
    val url: String,
    val name: String,
    val size: Int,
    val type: String
) : Parcelable
