package com.raghav.jetstar.domain.repository

import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.util.Resource

interface HomeScreenRepository {

    suspend fun getPopularMovies(): Resource<List<Movie>>
    suspend fun getTrendingMovies(): Resource<List<Movie>>
    suspend fun getTopRatedMovies(): Resource<List<Movie>>
    suspend fun getNowPlayingMovies(): Resource<List<Movie>>
}
