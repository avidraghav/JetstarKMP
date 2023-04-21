package com.raghav.jetstar.data.repository

import com.raghav.jetstar.data.sources.remote.MovieService
import com.raghav.jetstar.domain.entity.Movie
import com.raghav.jetstar.domain.repository.MovieRepository
import com.raghav.jetstar.util.Resource

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {
    override suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return when (val response = movieService.getTopRatedMovies()) {
            is Resource.Error -> {
                Resource.Error(response.exception)
            }
            is Resource.Success -> {
                Resource.Success(response.data)
            }
        }
    }
}
