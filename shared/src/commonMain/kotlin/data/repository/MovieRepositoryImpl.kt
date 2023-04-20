package data.repository

import data.model.toDomain
import data.sources.remote.MovieService
import domain.entity.Movie
import domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {
    override suspend fun getTopRatedMovies(): List<Movie> {
        return movieService.getTopRatedMovies().map {
            it.toDomain()
        }
    }
}
