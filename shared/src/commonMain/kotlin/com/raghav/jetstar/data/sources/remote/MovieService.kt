package com.raghav.jetstar.data.sources.remote

import com.raghav.jetstar.data.model.TrendingMediaResponseDto
import com.raghav.jetstar.util.Resource

interface MovieService {
    suspend fun getTrending(): Resource<TrendingMediaResponseDto>
    suspend fun getPopular(): Resource<TrendingMediaResponseDto>
    suspend fun getTopRated(): Resource<TrendingMediaResponseDto>
    suspend fun getNowPlaying(): Resource<TrendingMediaResponseDto>
}
