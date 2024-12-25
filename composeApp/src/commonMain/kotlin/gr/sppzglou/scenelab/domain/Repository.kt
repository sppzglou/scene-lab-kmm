package gr.sppzglou.scenelab.domain

import gr.sppzglou.scenelab.domain.models.CreditsDomainModel
import gr.sppzglou.scenelab.domain.models.MovieDomainModel
import gr.sppzglou.scenelab.domain.models.ReviewDomainModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchMovies(page: Int): List<MovieDomainModel>

    fun fetchFavoritesMoviesFlow(): Flow<List<MovieDomainModel>>

    fun fetchMovieFlow(id: Int): Flow<MovieDomainModel>

    suspend fun fetchSimilarMovies(id: Int): List<MovieDomainModel>

    suspend fun fetchCredits(id: Int): CreditsDomainModel

    suspend fun fetchReviews(id: Int): List<ReviewDomainModel>

    fun isFavoriteFlow(id: Int): Flow<Boolean>

    suspend fun addFavorite(id: Int)

    suspend fun removeFavorite(id: Int)

}