package com.raghav.jetstar.data.sources.remote

import com.raghav.jetstar.data.model.TrendingMediaResponseDto
import com.raghav.jetstar.util.Resource

interface MovieService {
    suspend fun getTrendingMedia(): Resource<TrendingMediaResponseDto>
}
