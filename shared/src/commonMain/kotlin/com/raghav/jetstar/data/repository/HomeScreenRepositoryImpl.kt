package com.raghav.jetstar.data.repository

import com.raghav.jetstar.data.model.toDomain
import com.raghav.jetstar.data.model.toDomain2
import com.raghav.jetstar.data.sources.remote.MovieService
import com.raghav.jetstar.database.Database
import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.domain.entity.trending.MovieResponse
import com.raghav.jetstar.domain.repository.HomeScreenRepository
import com.raghav.jetstar.util.Resource

class HomeScreenRepositoryImpl(
    private val movieService: MovieService,
    private val database: Database
) : HomeScreenRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        val cachedResponse = database.getPopularMovies()
        return if (cachedResponse.isNotEmpty()) {
            Resource.Success(cachedResponse)
        } else {
            when (val response = movieService.getPopular()) {
                is Resource.Error -> {
                    Resource.Error(response.exception)
                }
                is Resource.Success -> {
                    database.clearDatabase()
                    database.insertMovies(response.data.toDomain2().filterNotNull())
                    Resource.Success(database.getPopularMovies())
                }
            }
        }
    }

    override suspend fun getTrendingMovies(): Resource<MovieResponse> {
        return when (val response = movieService.getTrending()) {
            is Resource.Error -> {
                Resource.Error(response.exception)
            }
            is Resource.Success -> {
                Resource.Success(response.data.toDomain())
            }
        }
    }

    override suspend fun getTopRatedMovies(): Resource<MovieResponse> {
        return when (val response = movieService.getTopRated()) {
            is Resource.Error -> {
                Resource.Error(response.exception)
            }
            is Resource.Success -> {
                Resource.Success(response.data.toDomain())
            }
        }
    }

    override suspend fun getNowPlayingMovies(): Resource<MovieResponse> {
        return when (val response = movieService.getNowPlaying()) {
            is Resource.Error -> {
                Resource.Error(response.exception)
            }
            is Resource.Success -> {
                Resource.Success(response.data.toDomain())
            }
        }
    }
}
