package com.raghav.jetstar.domain.repository

import com.raghav.jetstar.domain.entity.trending.TrendingMediaResponse
import com.raghav.jetstar.util.Resource

interface HomeScreenRepository {

    suspend fun getPopularMovies(): Resource<TrendingMediaResponse>
    suspend fun getTrendingMovies(): Resource<TrendingMediaResponse>
    suspend fun getTopRatedMovies(): Resource<TrendingMediaResponse>
    suspend fun getNowPlayingMovies(): Resource<TrendingMediaResponse>
}
