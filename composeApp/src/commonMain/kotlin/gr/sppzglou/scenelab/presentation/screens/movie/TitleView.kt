package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.dpToPx
import gr.sppzglou.composebooster.mutableRem
import gr.sppzglou.composebooster.widthPx
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.Clr.TextColor2
import gr.sppzglou.scenelab.presentation.models.MovieUiModel
import gr.sppzglou.scenelab.presentation.views.FavBtn

@Composable
fun TitleView(movie: MovieUiModel, onFavClick: (Boolean) -> Unit) {
    Column(Modifier.padding(horizontal = 20.dp)) {
        var w by mutableRem(0)
        Row(
            Modifier
                .fillMaxWidth()
                .onSizeChanged {
                    w = it.width
                },
            Arrangement.SpaceBetween
        ) {
            Column(Modifier.widthPx { w - 70.dpToPx }) {
                Text(
                    movie.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor1
                )
                Text(
                    "(${movie.originalTitle})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor2
                )
            }
            Spacer(20)
            FavBtn(movie.isFavorite, 50) {
                onFavClick(!movie.isFavorite)
            }
        }
        Spacer(20)
        Text(
            movie.genres,
            color = TextColor1
        )
    }
}