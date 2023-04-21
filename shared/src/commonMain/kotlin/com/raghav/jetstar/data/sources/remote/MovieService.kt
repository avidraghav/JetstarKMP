package com.raghav.jetstar.data.sources.remote

import com.raghav.jetstar.domain.entity.Movie
import com.raghav.jetstar.util.Resource

interface MovieService {
    suspend fun getTopRatedMovies(): Resource<List<Movie>>
}
