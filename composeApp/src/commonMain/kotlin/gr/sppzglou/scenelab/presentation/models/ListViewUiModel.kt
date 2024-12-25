package gr.sppzglou.scenelab.presentation.models

data class ListViewUiModel(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val releaseDate: String,
    val voteAverage: Double,
    val poster: String?,
    val isFavorite: Boolean,
    val page: Int
)