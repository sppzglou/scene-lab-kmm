package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.OnBackPress
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.SpacerNav
import gr.sppzglou.composebooster.bottomsheet.rememberBottomSheetState
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.presentation.BottomSheet
import gr.sppzglou.scenelab.presentation.models.MovieUiModel
import gr.sppzglou.scenelab.presentation.navigator
import gr.sppzglou.scenelab.presentation.popBackStack

@Composable
fun CrewView(movie: MovieUiModel) {
    val nav = navigator()
    val sheet = rememberBottomSheetState()

    Column(Modifier.padding(horizontal = 20.dp)) {
        Spacer(20)
        Text(
            "Directors",
            color = TextColor1,
            fontWeight = FontWeight.Bold
        )
        Spacer()
        movie.directors.forEach {
            PersonItemView(it.name, it.photo)
        }
        if (movie.crew.isNotEmpty()) {
            Spacer(20)
            CategoryItemView("Crew") {
                sheet.show()
            }

            BottomSheet(sheet) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 20.dp)
                ) {
                    items(movie.crew) {
                        PersonItemView(it.name, it.photo, it.job)
                    }
                    SpacerNav(20)
                }

                OnBackPress {
                    if (sheet.isVisible) sheet.hide()
                    else nav.popBackStack()
                }
            }
        }
    }
}