package com.raghav.jetstar.domain.repository

import com.raghav.jetstar.domain.entity.trending.TrendingMediaResponse
import com.raghav.jetstar.util.Resource

interface MovieRepository {

    suspend fun getTopRatedMovies(): Resource<TrendingMediaResponse>
}
