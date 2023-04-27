package com.raghav.jetstar.data.sources.remote

import com.raghav.jetstar.data.model.MovieResponseDto
import com.raghav.jetstar.util.Resource

interface MovieService {
    suspend fun getTrending(): Resource<MovieResponseDto>
    suspend fun getPopular(): Resource<MovieResponseDto>
    suspend fun getTopRated(): Resource<MovieResponseDto>
    suspend fun getNowPlaying(): Resource<MovieResponseDto>
}
