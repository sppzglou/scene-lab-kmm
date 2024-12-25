package gr.sppzglou.scenelab.presentation.vm

import cafe.adriel.voyager.core.model.screenModelScope
import gr.sppzglou.composebooster.DatePattern
import gr.sppzglou.composebooster.formatDate
import gr.sppzglou.composebooster.tStr
import gr.sppzglou.scenelab.ImageUrl
import gr.sppzglou.scenelab.domain.cases.AddRemoveFavoriteUseCase
import gr.sppzglou.scenelab.domain.cases.GetMovieDetailsUseCase
import gr.sppzglou.scenelab.presentation.BasicScreenModel
import gr.sppzglou.scenelab.presentation.models.CastUiModel
import gr.sppzglou.scenelab.presentation.models.CrewUiModel
import gr.sppzglou.scenelab.presentation.models.MovieUiModel
import gr.sppzglou.scenelab.presentation.models.ReviewUiModel
import gr.sppzglou.scenelab.presentation.models.SimilarUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class MovieScreenModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addRemoveFavoriteUseCase: AddRemoveFavoriteUseCase
) : BasicScreenModel() {

    private val moviesCache = mutableMapOf<Int, StateFlow<MovieUiModel?>>()

    fun movie(movieId: Int): StateFlow<MovieUiModel?> {
        return moviesCache.getOrPut(movieId) {
            getMovieDetailsUseCase(movieId)
                .mapLatest { details ->
                    val movieDetails = details.first
                    val movieReviews = details.second
                    val similarMovies = details.third

                    movieDetails.let { movieWithFavorite ->
                        val domainMovie = movieWithFavorite.movie
                        val isFavorite = movieWithFavorite.isFavorite

                        MovieUiModel(
                            id = domainMovie.id,
                            title = domainMovie.title,
                            originalTitle = domainMovie.originalTitle,
                            releaseDate = domainMovie.releaseDate?.formatDate().tStr(),
                            voteAverage = domainMovie.voteAverage,
                            coverUrl = domainMovie.backdropPath,
                            posterUrl = domainMovie.posterPath,
                            genres = domainMovie.genres.joinToString(", "),
                            overview = domainMovie.overview,
                            isFavorite = isFavorite,
                            similar = similarMovies.map { similarMovie ->
                                SimilarUiModel(
                                    id = similarMovie.id,
                                    posterUrl = similarMovie.posterPath
                                )
                            },
                            directors = domainMovie.crew.filter { crewMember -> crewMember.job == "Director" }
                                .map { director ->
                                    CrewUiModel(
                                        name = director.name,
                                        photo = director.photo?.let { photoPath -> "$ImageUrl$photoPath" }
                                            .tStr(),
                                        job = director.job
                                    )
                                },
                            crew = domainMovie.crew.filter { crewMember -> crewMember.job != "Director" }
                                .map { crew ->
                                    CrewUiModel(
                                        name = crew.name,
                                        photo = crew.photo,
                                        job = crew.job
                                    )
                                },
                            cast = domainMovie.cast.map { castMember ->
                                CastUiModel(
                                    name = castMember.name,
                                    photo = castMember.photo,
                                    character = castMember.character
                                )
                            },
                            reviews = movieReviews.map { review ->
                                ReviewUiModel(
                                    author = review.author,
                                    content = review.content,
                                    id = review.id,
                                    date = review.date.formatDate(from = DatePattern.ISO_DATE_TIME_UTC),
                                    url = review.url
                                )
                            }
                        )
                    }
                }
                .stateIn(
                    screenModelScope,
                    SharingStarted.WhileSubscribed(5000),
                    null
                )
        }
    }

    fun addRemoveFav(id: Int, isFav: Boolean) = launch {
        addRemoveFavoriteUseCase(id, isFav)
    }
}

