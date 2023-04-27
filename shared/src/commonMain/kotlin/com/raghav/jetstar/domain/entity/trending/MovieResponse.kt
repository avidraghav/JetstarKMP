package com.raghav.jetstar.domain.entity.trending

data class MovieResponse(
    val page: Int?,
    val results: List<Movie?>?,
    val totalPages: Int?,
    val totalResults: Int?
)
