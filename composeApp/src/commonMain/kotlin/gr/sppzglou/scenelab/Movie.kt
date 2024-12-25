package gr.sppzglou.scenelab

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>?,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    val title: String,
    @SerialName("vote_average")
    val voteAverage: Double?,
//    val cast: List<Cast> = listOf(),
//    val crew: List<Crew> = listOf()
) {
    fun getBackdropUrl() = backdropPath?.let { "${ImageUrl}$it" }
    fun getPosterUrl() = posterPath?.let { "${ImageUrl}$it" }

    /* fun getTitle(lang: String) =
         if (lang == Lang.el) titleEl ?: titleEn else titleEn ?: titleEl

     fun getOverview(lang: String) =
         if (lang == Lang.el) overviewEl ?: overviewEn else overviewEn ?: overviewEl*/
}
