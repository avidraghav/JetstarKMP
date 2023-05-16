package com.raghav.jetstar.domain.repository

import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.domain.entity.trending.MovieResponse
import com.raghav.jetstar.util.Resource

interface HomeScreenRepository {

    suspend fun getPopularMovies(): Resource<List<Movie>>
    suspend fun getTrendingMovies(): Resource<MovieResponse>
    suspend fun getTopRatedMovies(): Resource<MovieResponse>
    suspend fun getNowPlayingMovies(): Resource<MovieResponse>
}
