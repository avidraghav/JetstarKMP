package com.raghav.jetstar.util

sealed class Resource<out T> {
    /**
     * Success represents that action taken has been completed
     * with valid data as result
     */
    class Success<T>(val data: T) : Resource<T>()

    /**
     * Error represents that action taken has not been completed du
     * to some exception or it has completed but the resulting data is
     * not valid
     */
    class Error(val exception: Exception) : Resource<Nothing>()
}
