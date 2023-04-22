package domain.repository

import domain.entity.Movie

interface MovieRepository {

    suspend fun getTopRatedMovies(): List<Movie>
}
