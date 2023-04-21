package com.raghav.jetstar.domain.repository

import com.raghav.jetstar.domain.entity.Movie
import com.raghav.jetstar.util.Resource

interface MovieRepository {

    suspend fun getTopRatedMovies(): Resource<List<Movie>>
}
