package data.sources

import Jetstar.shared.BuildConfig
import data.model.MovieDto
import data.sources.remote.MovieService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import util.Constants

class MovieServiceImpl(
    private val client: HttpClient
) : MovieService {
    override suspend fun getTopRatedMovies(): List<MovieDto> {
        return try {
            val response = client.get {
                url(Constants.POPULAR_MOVIES_URL)
                parameter("language", Constants.LANG_ENG)
                parameter("page", 1)
                parameter("api_key", BuildConfig.API_KEY)
            }
            response.body()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}
