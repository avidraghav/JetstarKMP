package data.model

import domain.entity.Movie
import kotlinx.serialization.SerialName
import util.Constants

data class MovieDto(
    val id: Long,
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backDropPath: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("original_language")
    val originalLanguage: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    @SerialName("release_date")
    val releaseDate: String,
    val video: Boolean,
    @SerialName("vote_average")
    val avgVote: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

fun MovieDto.toDomain() = Movie(
    id = id,
    isAdult = adult,
    backDropUrl = Constants.IMAGE_BASE_URL_W500 + backDropPath,
    posterUrl = Constants.IMAGE_BASE_URL_W500 + posterPath,
    genreIds = genreIds,
    language = originalLanguage,
    title = title,
    overview = overview,
    popularity = popularity,
    releaseDate = releaseDate,
    isVideoAvailable = video,
    avgVote = avgVote,
    voteCount = voteCount
)
