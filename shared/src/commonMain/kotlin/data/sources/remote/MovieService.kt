package data.sources.remote

import data.model.MovieDto

interface MovieService {
    suspend fun getTopRatedMovies(): List<MovieDto>
}
