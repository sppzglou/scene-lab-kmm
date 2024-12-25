package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.checkOrientation
import gr.sppzglou.composebooster.mutableRem
import gr.sppzglou.composebooster.pxToDp
import gr.sppzglou.scenelab.Clr.BackgroundColor
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.presentation.models.SimilarUiModel
import gr.sppzglou.scenelab.presentation.navigate
import gr.sppzglou.scenelab.presentation.navigator
import gr.sppzglou.scenelab.presentation.views.ImageView

@Composable
fun SimilarMoviesView(similar: List<SimilarUiModel>) {
    val nav = navigator()
    var w by mutableRem(0f)

    Column(Modifier.fillMaxWidth().onSizeChanged {
        w = it.width.pxToDp
    }) {
        Spacer(20)
        Text(
            "Similar movies",
            Modifier.padding(horizontal = 20.dp),
            fontWeight = FontWeight.Bold,
            color = TextColor1
        )
        Spacer()
        LazyRow {
            items(similar.size) { i ->
                ImageView(similar[i].posterUrl,
                    Modifier
                        .padding(
                            start = if (i == 0) 20.dp else 10.dp,
                            end = if (i == similar.size - 1) 20.dp else 0.dp
                        )
                        .width((w * checkOrientation(0.35, 0.2)).dp)
                        .aspectRatio(0.66f)
                        .shadow(
                            5.dp,
                            RoundedCornerShape(15.dp),
                            spotColor = TextColor1
                        )
                        .background(BackgroundColor)
                        .Click {
                            nav.navigate(MovieScreen(similar[i].id))
                        })
            }
        }
    }
}