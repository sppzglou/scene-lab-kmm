package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.formatToStr
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.Clr.TextColor2
import gr.sppzglou.scenelab.presentation.models.MovieUiModel
import gr.sppzglou.scenelab.presentation.views.RateView

@Composable
fun DetailsView(movie: MovieUiModel) {
    Column(Modifier.padding(horizontal = 20.dp)) {
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
                Modifier.width(150.dp),
                movie.voteAverage
            )
        }
        Spacer(20)
        Text(
            "Description",
            color = TextColor1,
            fontWeight = FontWeight.Bold
        )
        Spacer()
        Text(
            movie.overview,
            color = TextColor1
        )
    }
}