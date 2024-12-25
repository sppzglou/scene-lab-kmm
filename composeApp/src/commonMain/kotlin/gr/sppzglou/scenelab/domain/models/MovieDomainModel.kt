package gr.sppzglou.scenelab.domain.models

data class MovieDomainModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genres: List<String>,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double,
    var crew: List<CrewDomainModel>,
    var cast: List<CastDomainModel>,
    val page: Int
)