package com.raghav.jetstar.data.repository

import com.raghav.jetstar.data.model.toDomain
import com.raghav.jetstar.data.sources.remote.MovieService
import com.raghav.jetstar.database.Database
import com.raghav.jetstar.database.MovieType
import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.domain.repository.HomeScreenRepository
import com.raghav.jetstar.util.Resource

class HomeScreenRepositoryImpl(
    private val movieService: MovieService,
    private val database: Database
) : HomeScreenRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        val cachedResponse = database.getMovies(MovieType.POPULAR)
        return if (cachedResponse.isNotEmpty()) {
            Resource.Success(cachedResponse)
        } else {
            when (val response = movieService.getPopular()) {
                is Resource.Error -> {
                    Resource.Error(response.exception)
                }
                is Resource.Success -> {
                    database.clearDatabase(MovieType.POPULAR)
                    database.insertMovies(
                        MovieType.POPULAR,
                        response.data.toDomain().filterNotNull()
                    )
                    Resource.Success(database.getMovies(MovieType.POPULAR))
                }
            }
        }
    }

    override suspend fun getTrendingMovies(): Resource<List<Movie>> {
        val cachedResponse = database.getMovies(MovieType.TRENDING)
        return if (cachedResponse.isNotEmpty()) {
            Resource.Success(cachedResponse)
        } else {
            when (val response = movieService.getTrending()) {
                is Resource.Error -> {
                    Resource.Error(response.exception)
                }
                is Resource.Success -> {
                    database.clearDatabase(MovieType.TRENDING)
                    database.insertMovies(
                        MovieType.TRENDING,
                        response.data.toDomain().filterNotNull()
                    )
                    Resource.Success(database.getMovies(MovieType.TRENDING))
                }
            }
        }
    }

    override suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        val cachedResponse = database.getMovies(MovieType.TOP_RATED)
        return if (cachedResponse.isNotEmpty()) {
            Resource.Success(cachedResponse)
        } else {
            when (val response = movieService.getTopRated()) {
                is Resource.Error -> {
                    Resource.Error(response.exception)
                }
                is Resource.Success -> {
                    database.clearDatabase(MovieType.TOP_RATED)
                    database.insertMovies(
                        MovieType.TOP_RATED,
                        response.data.toDomain().filterNotNull()
                    )
                    Resource.Success(database.getMovies(MovieType.TOP_RATED))
                }
            }
        }
    }

    override suspend fun getNowPlayingMovies(): Resource<List<Movie>> {
        val cachedResponse = database.getMovies(MovieType.NOW_PLAYING)
        return if (cachedResponse.isNotEmpty()) {
            Resource.Success(cachedResponse)
        } else {
            when (val response = movieService.getNowPlaying()) {
                is Resource.Error -> {
                    Resource.Error(response.exception)
                }
                is Resource.Success -> {
                    database.clearDatabase(MovieType.NOW_PLAYING)
                    database.insertMovies(
                        MovieType.NOW_PLAYING,
                        response.data.toDomain().filterNotNull()
                    )
                    Resource.Success(database.getMovies(MovieType.NOW_PLAYING))
                }
            }
        }
    }
}
