package com.raghav.jetstar.domain.entity.trending

data class TrendingMediaResponse(
    val page: Int?,
    val results: List<TrendingMedia?>?,
    val totalPages: Int?,
    val totalResults: Int?
)
