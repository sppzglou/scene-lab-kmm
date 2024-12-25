package gr.sppzglou.scenelab.domain.models

data class MovieDomainModelWithFavorite(
    val movie: MovieDomainModel,
    val isFavorite: Boolean
)