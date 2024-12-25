package gr.sppzglou.scenelab.presentation.models


data class MovieUiModel(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val releaseDate: String,
    val voteAverage: Double,
    val coverUrl: String?,
    val posterUrl: String?,
    val genres: String,
    val overview: String,
    val isFavorite: Boolean,
    val similar: List<SimilarUiModel>,
    val directors: List<CrewUiModel>,
    val crew: List<CrewUiModel>,
    val cast: List<CastUiModel>,
    val reviews: List<ReviewUiModel>
)