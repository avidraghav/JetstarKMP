package com.raghav.jetstar.database

import com.raghav.jetstar.domain.entity.trending.Movie
import com.squareup.sqldelight.ColumnAdapter

class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split(",")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = ",")
    }
    private val listOfIntsAdapter = object : ColumnAdapter<List<Int>, String> {
        override fun decode(databaseValue: String): List<Int> {
            return if (databaseValue.isEmpty()) {
                emptyList<Int>()
            } else {
                databaseValue.split(",").map { it.toInt() }
            }
        }

        override fun encode(value: List<Int>) = value.joinToString(separator = ",")
    }

    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        PopularMoviesAdapter = PopularMovies.Adapter(
            genreIdsAdapter = listOfIntsAdapter,
            originCountryAdapter = listOfStringsAdapter
        )
    )
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllPopularMovies()
        }
    }

    internal fun getPopularMovies(): List<Movie> {
        return dbQuery.getPopularMovies(::mapMovies).executeAsList()
    }

    internal fun insertMovies(movies: List<Movie>) {
        dbQuery.transaction {
            movies.forEach { movie ->
                insertMovie(movie)
            }
        }
    }

    private fun mapMovies(
        adult: Boolean?,
        backdropPath: String?,
        firstAirDate: String?,
        genreIds: List<Int>?,
        id: Long?,
        mediaType: String?,
        name: String?,
        originCountry: List<String>?,
        originalLanguage: String?,
        originalName: String?,
        originalTitle: String?,
        overview: String?,
        popularity: Double?,
        posterPath: String?,
        releaseDate: String?,
        title: String?,
        video: Boolean?,
        voteAverage: Double?,
        voteCount: Long?
    ): Movie {
        return Movie(
            adult = adult,
            backdropPath = backdropPath,
            firstAirDate = firstAirDate,
            genreIds = genreIds,
            id = id?.toInt(),
            mediaType = mediaType,
            name = name,
            originCountry = originCountry,
            originalLanguage = originalLanguage,
            originalName = originalName,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount?.toInt()
        )
    }

    private fun insertMovie(movie: Movie) {
        dbQuery.insertPopularMovie(
            adult = movie.adult,
            backdropPath = movie.backdropPath,
            firstAirDate = movie.firstAirDate,
            genreIds = movie.genreIds?.filterNotNull() ?: emptyList(),
            id = movie.id?.toLong(),
            mediaType = movie.mediaType,
            name = movie.name,
            originCountry = movie.originCountry?.filterNotNull() ?: emptyList(),
            originalLanguage = movie.originalLanguage,
            originalName = movie.originalName,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount?.toLong()
        )
    }
}
