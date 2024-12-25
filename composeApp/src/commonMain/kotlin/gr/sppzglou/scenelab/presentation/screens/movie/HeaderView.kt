package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.ScreenOrientation
import gr.sppzglou.composebooster.checkOrientation
import gr.sppzglou.composebooster.getScreenOrientation
import gr.sppzglou.composebooster.pxToDp
import gr.sppzglou.composebooster.safePadding
import gr.sppzglou.scenelab.presentation.models.MovieUiModel
import gr.sppzglou.scenelab.presentation.views.ImageView

@Composable
fun HeaderView(
    movie: MovieUiModel,
    coverRatio: Float,
    blurProgress: Float,
    coverHeight: Float,
    onFavClick: (Boolean) -> Unit
) {
    Box {
        ImageView(
            movie.coverUrl,
            Modifier
                .fillMaxWidth()
                .aspectRatio(coverRatio)
                .alpha(0.5f)
                .blur((50 * blurProgress).dp)
        )
        ImageView(
            movie.posterUrl,
            Modifier
                .padding(top = (coverHeight.pxToDp * 0.5).dp, start = 20.dp)
                .fillMaxWidth(checkOrientation(0.4f, 0.2f))
                .aspectRatio(0.66f)
                .clip(RoundedCornerShape(10.dp))
        )
        if (getScreenOrientation() != ScreenOrientation.Portrait) {
            Row(
                Modifier.fillMaxWidth()
                    .padding(top = safePadding(coverHeight.pxToDp - 25).dp, start = 30.dp),
                Arrangement.End
            ) {
                Box(Modifier.fillMaxWidth(0.8f)) {
                    TitleView(movie, onFavClick)
                }
            }
        }
    }
}
