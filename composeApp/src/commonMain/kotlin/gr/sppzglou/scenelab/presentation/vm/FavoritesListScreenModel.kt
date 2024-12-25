package gr.sppzglou.scenelab.presentation.vm

import cafe.adriel.voyager.core.model.screenModelScope
import gr.sppzglou.composebooster.formatDate
import gr.sppzglou.composebooster.tStr
import gr.sppzglou.scenelab.domain.cases.AddRemoveFavoriteUseCase
import gr.sppzglou.scenelab.domain.cases.GetFavoriteMoviesUseCase
import gr.sppzglou.scenelab.presentation.BasicScreenModel
import gr.sppzglou.scenelab.presentation.models.ListViewUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class FavoritesListScreenModel(
    getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addRemoveFavoriteUseCase: AddRemoveFavoriteUseCase
) : BasicScreenModel() {

    val movies: StateFlow<List<ListViewUiModel>> = getFavoriteMoviesUseCase()
        .mapLatest { moviesWithFavorites ->
            moviesWithFavorites.map {
                ListViewUiModel(
                    id = it.id,
                    title = it.title,
                    originalTitle = it.originalTitle,
                    releaseDate = it.releaseDate?.formatDate().tStr(),
                    voteAverage = it.voteAverage,
                    poster = it.posterPath,
                    isFavorite = true,
                    page = 1
                )
            }
        }
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addRemoveFav(id: Int, isFav: Boolean) = launch {
        addRemoveFavoriteUseCase(id, isFav)
    }
}

