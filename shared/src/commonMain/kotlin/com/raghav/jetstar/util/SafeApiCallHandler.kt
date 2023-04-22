package com.raghav.jetstar.util

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> safeApiCallHandler(block: () -> HttpResponse): Resource<T> {
    return try {
        // Getting API Response
        val apiCallResponse = block()
        Resource.Success(apiCallResponse.body())
    } catch (e: RedirectResponseException) {
        // 3xx - responses
        println("Error: ${e.response.status.description}")
        Resource.Error(e)
    } catch (e: ClientRequestException) {
        // 4xx - responses
        println("Error: ${e.response.status.description}")
        Resource.Error(e)
    } catch (e: NoTransformationFoundException) {
        Resource.Error(e)
    } catch (e: ServerResponseException) {
        // 5xx - responses
        println("Error: ${e.response.status.description}")
        Resource.Error(e)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        Resource.Error(e)
    }
}
