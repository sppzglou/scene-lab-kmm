package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import gr.sppzglou.composebooster.LocalScreenSizes
import gr.sppzglou.composebooster.ScreenOrientation
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.SpacerNav
import gr.sppzglou.composebooster.checkOrientation
import gr.sppzglou.composebooster.getScreenOrientation
import gr.sppzglou.scenelab.Clr.BackgroundColor
import gr.sppzglou.scenelab.presentation.BaseScreen
import gr.sppzglou.scenelab.presentation.vm.MovieScreenModel


class MovieScreen(private val id: Int) : BaseScreen {

    override val key: String = "${this::class.simpleName}-$id"

    @Composable
    override fun BoxScope.View() {
        val screenModel: MovieScreenModel = getScreenModel()
        val movie by screenModel.movie(id).collectAsStateWithLifecycle()
        val hazeState = remember { HazeState() }
        val scrollState = rememberLazyListState()
        val screenWidthPx = LocalScreenSizes.current.px.width

        val coverRatio = remember(getScreenOrientation()) {
            checkOrientation(1.5f, 4f)
        }
        val coverHeight = remember(screenWidthPx, coverRatio) {
            checkOrientation(
                screenWidthPx / coverRatio,
                screenWidthPx / coverRatio
            )
        }
        val blurProgress = remember(scrollState.firstVisibleItemScrollOffset, coverRatio) {
            if (scrollState.firstVisibleItemIndex > 0) 1f
            else {
                if (scrollState.layoutInfo.viewportSize.height == 0)
                    return@remember 0f

                scrollState.firstVisibleItemScrollOffset /
                        coverHeight * 100f / 50f //at 50% of cover
            }
        }

        BarView(
            Modifier.zIndex(1f)
                .hazeChild(
                    hazeState,
                    HazeStyle(
                        backgroundColor = BackgroundColor,
                        tint = null,
                        blurRadius = (50.0 * blurProgress).dp
                    )
                ),
            movie
        )

        LoadingView(coverHeight, movie, scrollState)

        AnimatedVisibility(
            movie != null,
            enter = fadeIn(animationSpec = tween(durationMillis = 3000)),
        ) {
            movie?.let { movie ->
                fun onFavClick(isFav: Boolean) {
                    screenModel.addRemoveFav(movie.id, isFav)
                }

                LazyColumn(Modifier.fillMaxSize().haze(hazeState), scrollState) {
                    item {
                        HeaderView(movie, coverRatio, blurProgress, coverHeight, ::onFavClick)

                        if (getScreenOrientation() == ScreenOrientation.Portrait) {
                            Spacer(20)
                            TitleView(movie, ::onFavClick)
                        }
                        DetailsView(movie)
                        if (movie.similar.isNotEmpty()) {
                            SimilarMoviesView(movie.similar)
                        }
                        CrewView(movie)
                        if (movie.cast.isNotEmpty()) {
                            CastView(movie.cast)
                        }
                        if (movie.reviews.isNotEmpty()) {
                            ReviewsView(movie.reviews)
                        }
                    }

                    SpacerNav(50)
                }
            }
        }
    }
}