package gr.sppzglou.scenelab.data

import gr.sppzglou.composebooster.AppHandler
import gr.sppzglou.scenelab.data.local.LocalDataSource
import gr.sppzglou.scenelab.data.remote.RemoteDataSource
import gr.sppzglou.scenelab.data.remote.toDomainModel
import gr.sppzglou.scenelab.data.remote.toMovieEntity
import gr.sppzglou.scenelab.domain.Repository
import gr.sppzglou.scenelab.domain.models.MovieDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest

class RepositoryImpl(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val handler: AppHandler
) : Repository {

    override suspend fun fetchMovies(page: Int) =
        remote.fetchMovies(page).results.map {
            it.toDomainModel(page = page)
        }

    override fun fetchFavoritesMoviesFlow(): Flow<List<MovieDomainModel>> =
        local.getFavoritesFlow().flatMapLatest { favoriteEntities ->
            if (favoriteEntities.isEmpty()) return@flatMapLatest flowOf(emptyList())

            // Δημιουργούμε ένα Flow για κάθε αγαπημένο ID
            val movieFlows = favoriteEntities.map { favorite ->
                fetchMovieFlow(favorite.id)
            }
            // Συνδυάζουμε όλα τα Flows σε μία λίστα
            combine(movieFlows) { moviesArray ->
                moviesArray.toList()
            }
        }

    override fun fetchMovieFlow(id: Int) = flow {
        handler.request {
            local.getMovie(id)?.toDomainModel()?.let {
                emit(it)
            } ?: run {
                val res = remote.fetchMovie(id)
                val credits = fetchCredits(id)
                val domainModel = res.toDomainModel(credits)
                emit(domainModel)
                local.insertMovie(domainModel.toMovieEntity())
            }
        }
    }

    override suspend fun fetchSimilarMovies(id: Int) =
        remote.fetchSimilarMovies(id).results.map {
            it.toDomainModel()
        }

    override suspend fun fetchCredits(id: Int) =
        remote.fetchCredits(id).credits.toDomainModel()

    override suspend fun fetchReviews(id: Int) =
        remote.fetchReviews(id).results.map {
            it.toDomainModel()
        }

    override fun isFavoriteFlow(id: Int) =
        local.getFavoriteFlow(id).mapLatest {
            it != null
        }

    override suspend fun addFavorite(id: Int) = local.addFavorite(id)

    override suspend fun removeFavorite(id: Int) = local.removeFavorite(id)
}