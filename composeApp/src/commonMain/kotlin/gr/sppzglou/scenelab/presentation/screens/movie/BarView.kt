package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import gr.sppzglou.scenelab.Clr.PrimaryColor
import gr.sppzglou.scenelab.presentation.models.MovieUiModel
import gr.sppzglou.scenelab.presentation.views.BackBtn

@Composable
fun BarView(modifier: Modifier, movie: MovieUiModel?) {
    Box(modifier) {
        if (movie == null) {
            LinearProgressIndicator(
                Modifier.blur(100.dp).statusBarsPadding().fillMaxWidth().height(50.dp),
                color = PrimaryColor.copy(0.5f),
                backgroundColor = Transparent,
            )
        }
        Box(
            Modifier
                .fillMaxWidth().statusBarsPadding().height(50.dp)
                .padding(vertical = 5.dp, horizontal = 20.dp)
        ) {
            BackBtn()
        }
    }
}