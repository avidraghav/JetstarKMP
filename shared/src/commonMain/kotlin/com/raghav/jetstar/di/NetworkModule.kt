package com.raghav.jetstar.di

import Jetstar.shared.BuildConfig
import com.raghav.jetstar.data.sources.MovieServiceImpl
import com.raghav.jetstar.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        explicitNulls = false
                    }
                )
            }

            defaultRequest {
                url {
                    url(Constants.BASE_URL)
                    protocol = URLProtocol.HTTPS
                    parameters.append("api_key", BuildConfig.API_KEY)
                }
            }
        }
    }

    single {
        MovieServiceImpl(get<HttpClient>())
    }
}
