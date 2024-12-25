package gr.sppzglou.scenelab.presentation.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import gr.sppzglou.composebooster.ScreenOrientation
import gr.sppzglou.composebooster.SpacerNav
import gr.sppzglou.composebooster.SpacerStatus
import gr.sppzglou.composebooster.getScreenOrientation
import gr.sppzglou.scenelab.Clr.BackgroundColor
import gr.sppzglou.scenelab.Clr.PrimaryColor
import gr.sppzglou.scenelab.presentation.BaseScreen
import gr.sppzglou.scenelab.presentation.navigate
import gr.sppzglou.scenelab.presentation.navigator
import gr.sppzglou.scenelab.presentation.screens.movie.MovieScreen
import gr.sppzglou.scenelab.presentation.vm.MoviesListScreenModel
import kotlinx.coroutines.flow.distinctUntilChanged

class MoviesListScreen : BaseScreen {

    @Composable
    override fun BoxScope.View() {
        val nav = navigator()
        val screenModel: MoviesListScreenModel = getScreenModel()
        val isLoading by screenModel.handler.inProgress.collectAsStateWithLifecycle()
        val list by screenModel.movies.collectAsStateWithLifecycle()
        val hazeState = remember { HazeState() }
        val orientationItems = remember(getScreenOrientation()) {
            if (getScreenOrientation() == ScreenOrientation.Portrait) 1 else 2
        }
        val gridState = rememberLazyGridState()

        LaunchedEffect(gridState) {
            snapshotFlow { gridState.isAtBottom() }
                .distinctUntilChanged()
                .collect { isAtBottom ->
                    if (isAtBottom) {
                        screenModel.loadNextPage()
                    }
                }
        }


        Box {
            BarView(
                Modifier
                    .zIndex(1f)
                    .hazeChild(
                        hazeState,
                        HazeStyle(
                            backgroundColor = BackgroundColor,
                            tint = HazeTint(BackgroundColor.copy(0.5f)),
                            blurRadius = 50.dp
                        )
                    )
            )
            Box(Modifier.haze(hazeState)) {
                if (isLoading) {
                    LinearProgressIndicator(
                        Modifier.zIndex(1f).statusBarsPadding().fillMaxWidth().height(50.dp),
                        color = PrimaryColor,
                        backgroundColor = Transparent,
                    )
                }
                LazyVerticalGrid(
                    GridCells.Fixed(orientationItems),
                    Modifier.fillMaxSize(),
                    gridState
                ) {
                    items(orientationItems) {
                        SpacerStatus(60)
                    }
                    items(list) { item ->
                        ListView(item, {
                            screenModel.addRemoveFav(item.id, it)
                        }) {
                            nav.navigate(MovieScreen(item.id))
                        }
                    }
                    if (list.isEmpty()) {
                        items(5) {
                            LoadingListView()
                        }
                    }
                    items(orientationItems) {
                        SpacerNav()
                    }
                }
            }
        }
    }
}

fun LazyGridState.isAtBottom(): Boolean {
    val visibleItems = layoutInfo.visibleItemsInfo
    val totalItems = layoutInfo.totalItemsCount

    if (visibleItems.isEmpty()) return false

    val lastVisibleItem = visibleItems.last()
    return lastVisibleItem.index == totalItems - 1
}