package gr.sppzglou.scenelab.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.formatToStr
import gr.sppzglou.composebooster.heightPx
import gr.sppzglou.composebooster.mutableRem
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.Clr.TextColor2
import gr.sppzglou.scenelab.presentation.models.ListViewUiModel
import gr.sppzglou.scenelab.presentation.views.FavBtn
import gr.sppzglou.scenelab.presentation.views.ImageView
import gr.sppzglou.scenelab.presentation.views.RateView

@Composable
fun ListView(
    movie: ListViewUiModel,
    onFavClick: (Boolean) -> Unit,
    onClick: suspend () -> Unit
) {

    Column {
        var itemHeight by mutableRem(0)
        Row(Modifier.fillMaxWidth().Click {
            onClick()
        }) {
            ImageView(
                movie.poster,
                Modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.4f)
                    .aspectRatio(0.66f)
                    .clip(RoundedCornerShape(10.dp))
                    .onSizeChanged {
                        itemHeight = it.height
                    }
            )
            Column(
                Modifier
                    .padding(5.dp)
                    .heightPx(itemHeight)
                    .padding(10.dp),
                Arrangement.SpaceBetween,
                Alignment.End
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        movie.title,
                        color = TextColor1,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "(${movie.originalTitle})",
                        color = TextColor2
                    )
                    Spacer(20)
                    Text(
                        movie.releaseDate,
                        color = TextColor2
                    )
                    Spacer(20)
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            movie.voteAverage.formatToStr(),
                            color = TextColor2
                        )
                        Spacer()

                        RateView(
                            Modifier.fillMaxWidth(0.6f),
                            movie.voteAverage
                        )
                    }
                }

                FavBtn(movie.isFavorite) {
                    onFavClick(!movie.isFavorite)
                }
            }
        }
    }
}