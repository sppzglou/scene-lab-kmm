package gr.sppzglou.scenelab.presentation.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import gr.sppzglou.composebooster.Click
import gr.sppzglou.composebooster.ScreenOrientation
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.SpacerNav
import gr.sppzglou.composebooster.SpacerStatus
import gr.sppzglou.composebooster.bottomsheet.rememberBottomSheetState
import gr.sppzglou.composebooster.getScreenOrientation
import gr.sppzglou.scenelab.Clr.BackgroundColor
import gr.sppzglou.scenelab.Clr.PrimaryColor
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.presentation.BaseScreen
import gr.sppzglou.scenelab.presentation.BottomSheet
import gr.sppzglou.scenelab.presentation.navigate
import gr.sppzglou.scenelab.presentation.navigator
import gr.sppzglou.scenelab.presentation.screens.movie.MovieScreen
import gr.sppzglou.scenelab.presentation.vm.FavoritesListScreenModel
import kotlinx.coroutines.launch

class FavoritesListScreen : BaseScreen {

    @Composable
    override fun BoxScope.View() {
        val nav = navigator()
        val screenModel: FavoritesListScreenModel = getScreenModel()
        val list by screenModel.movies.collectAsStateWithLifecycle()
        val hazeState = remember { HazeState() }
        val orientationItems = remember(getScreenOrientation()) {
            if (getScreenOrientation() == ScreenOrientation.Portrait) 1 else 2
        }
        val sheet = rememberBottomSheetState()
        val scope = rememberCoroutineScope()
        var clicked = remember<Int?> { null }

        Box {
            BarPopularView(
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
                LazyVerticalGrid(
                    GridCells.Fixed(orientationItems)
                ) {
                    items(orientationItems) {
                        SpacerStatus(60)
                    }
                    items(list) { item ->
                        ListView(item, {
                            scope.launch {
                                clicked = item.id
                                sheet.show()
                            }
                        }) {
                            nav.navigate(MovieScreen(item.id))
                        }
                    }
                    if (list.isEmpty()) {
                        item {
                            Text(
                                "No favorites yet!",
                                Modifier.padding(20.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextColor1
                            )
                        }
                    }
                    items(orientationItems) {
                        SpacerNav()
                    }
                }
            }
        }

        BottomSheet(sheet) {
            Column(Modifier.navigationBarsPadding().padding(20.dp)) {
                Text(
                    "Are you sure you want to remove this movie from your favorites?",
                    fontSize = 18.sp,
                    color = TextColor1
                )
                Spacer(20)
                Row(Modifier.fillMaxWidth(), Arrangement.End) {
                    Text(
                        "Yes",
                        Modifier
                            .clip(CircleShape)
                            .background(PrimaryColor)
                            .Click {
                                clicked?.let {
                                    screenModel.addRemoveFav(it, false)
                                    clicked = null
                                    sheet.hide()
                                }
                            }
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        color = TextColor1,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}