package gr.sppzglou.scenelab.domain.cases

import gr.sppzglou.composebooster.AppHandler
import gr.sppzglou.scenelab.domain.Repository
import gr.sppzglou.scenelab.domain.models.MovieDomainModel
import gr.sppzglou.scenelab.domain.models.MovieDomainModelWithFavorite
import gr.sppzglou.scenelab.domain.models.ReviewDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest

class GetMovieDetailsUseCase(
    private val repo: Repository,
    private val handler: AppHandler
) {
    operator fun invoke(id: Int): Flow<Triple<MovieDomainModelWithFavorite, List<ReviewDomainModel>, List<MovieDomainModel>>> {
        return repo.fetchMovieFlow(id).flatMapLatest { movie ->
            combine(
                flowOf(movie),
                repo.isFavoriteFlow(movie.id)
            ) { movieData, isFavorite ->
                MovieDomainModelWithFavorite(movieData, isFavorite)
            }.mapLatest { movieWithFavorite ->
                handler.request {
                    val similar = repo.fetchSimilarMovies(id)
                    val reviews = repo.fetchReviews(id)

                    Triple(movieWithFavorite, reviews, similar)
                } ?: Triple(
                    MovieDomainModelWithFavorite(movie, false),
                    emptyList(),
                    emptyList()
                )
            }
        }
    }
}