package gr.sppzglou.scenelab.domain.cases

import gr.sppzglou.scenelab.domain.Repository
import gr.sppzglou.scenelab.domain.models.MovieDomainModel
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(
    private val repo: Repository
) {

    operator fun invoke(): Flow<List<MovieDomainModel>> =
        repo.fetchFavoritesMoviesFlow()
}