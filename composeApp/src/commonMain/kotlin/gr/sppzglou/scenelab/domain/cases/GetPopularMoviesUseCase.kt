package gr.sppzglou.scenelab.domain.cases

import gr.sppzglou.composebooster.AppHandler
import gr.sppzglou.scenelab.domain.Repository
import gr.sppzglou.scenelab.domain.models.MovieDomainModel
import gr.sppzglou.scenelab.domain.models.MovieDomainModelWithFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class GetPopularMoviesUseCase(
    private val repo: Repository,
    private val handler: AppHandler
) {
    private val loadedMovies = MutableStateFlow<List<MovieDomainModel>>(emptyList())

    // Επιστροφή ενός Flow που περιέχει τις ταινίες με την κατάσταση αγαπημένων
    operator fun invoke(): Flow<List<MovieDomainModelWithFavorite>> {
        return loadedMovies.flatMapLatest { movies ->
            // Δημιουργούμε Map των ταινιών με το ID τους για αποδοτικότερο συνδυασμό
            val movieFlows = movies.associateBy { it.id }.mapValues { (_, movie) ->
                combine(
                    flowOf(movie),
                    repo.isFavoriteFlow(movie.id)
                ) { movieData, isFavorite ->
                    MovieDomainModelWithFavorite(movieData, isFavorite)
                }
            }

            // Συνδυάζουμε τα flows χωρίς να αναδημιουργούμε τη λίστα
            combine(movieFlows.values) { moviesWithFavorites ->
                moviesWithFavorites.toList()
            }
        }
    }

    suspend fun loadNextPage() {
        handler.request(true) {
            val nextPage = (loadedMovies.value.lastOrNull()?.page ?: 0) + 1

            // Φέρνουμε τις νέες ταινίες
            val newMovies = repo.fetchMovies(nextPage)

            // Προσθέτουμε τις νέες ταινίες στα ήδη φορτωμένα
            loadedMovies.value += newMovies
        }
    }
}