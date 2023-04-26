package com.raghav.jetstar.data.repository

import com.raghav.jetstar.data.model.toDomain
import com.raghav.jetstar.data.sources.remote.MovieService
import com.raghav.jetstar.domain.entity.trending.TrendingMediaResponse
import com.raghav.jetstar.domain.repository.HomeScreenRepository
import com.raghav.jetstar.util.Resource

class HomeScreenRepositoryImpl(
    private val movieService: MovieService
) : HomeScreenRepository {
    override suspend fun getTopRatedMovies(): Resource<TrendingMediaResponse> {
        return when (val response = movieService.getTrendingMedia()) {
            is Resource.Error -> {
                Resource.Error(response.exception)
            }
            is Resource.Success -> {
                Resource.Success(response.data.toDomain())
            }
        }
    }
}
