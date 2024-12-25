package gr.sppzglou.scenelab.presentation.vm

import cafe.adriel.voyager.core.model.screenModelScope
import gr.sppzglou.composebooster.formatDate
import gr.sppzglou.composebooster.tStr
import gr.sppzglou.scenelab.domain.cases.AddRemoveFavoriteUseCase
import gr.sppzglou.scenelab.domain.cases.GetPopularMoviesUseCase
import gr.sppzglou.scenelab.presentation.BasicScreenModel
import gr.sppzglou.scenelab.presentation.models.ListViewUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class MoviesListScreenModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val addRemoveFavoriteUseCase: AddRemoveFavoriteUseCase
) : BasicScreenModel() {

    init {
        loadNextPage()
    }

    val movies: StateFlow<List<ListViewUiModel>> = getPopularMoviesUseCase()
        .mapLatest { moviesWithFavorites ->
            moviesWithFavorites.map {
                ListViewUiModel(
                    id = it.movie.id,
                    title = it.movie.title,
                    originalTitle = it.movie.originalTitle,
                    releaseDate = it.movie.releaseDate?.formatDate().tStr(),
                    voteAverage = it.movie.voteAverage,
                    poster = it.movie.posterPath,
                    isFavorite = it.isFavorite,
                    page = it.movie.page
                )
            }
        }
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun loadNextPage() = launch {
        getPopularMoviesUseCase.loadNextPage()
    }

    fun addRemoveFav(id: Int, isFav: Boolean) = launch {
        addRemoveFavoriteUseCase(id, isFav)
    }
}

