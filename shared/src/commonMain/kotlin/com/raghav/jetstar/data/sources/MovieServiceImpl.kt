package com.raghav.jetstar.data.sources

import Jetstar.shared.BuildConfig
import com.raghav.jetstar.data.sources.remote.MovieService
import com.raghav.jetstar.domain.entity.Movie
import com.raghav.jetstar.util.Constants
import com.raghav.jetstar.util.Resource
import com.raghav.jetstar.util.safeApiCallHandler
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class MovieServiceImpl(
    private val client: HttpClient
) : MovieService {
    override suspend fun getTopRatedMovies(): Resource<List<Movie>> = safeApiCallHandler {
        client.get {
            url(Constants.POPULAR_MOVIES_URL)
            parameter("language", Constants.LANG_ENG)
            parameter("page", 1)
            parameter("api_key", BuildConfig.API_KEY)
        }
    }
}
