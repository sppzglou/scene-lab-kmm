package gr.sppzglou.scenelab.presentation.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.ShimmerEffect
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.scenelab.Clr.TextColor1

@Composable
fun LoadingListView() {
    Row(Modifier.fillMaxWidth()) {
        ShimmerEffect(
            Modifier
                .padding(5.dp)
                .fillMaxWidth(0.4f)
                .aspectRatio(0.66f)
                .clip(RoundedCornerShape(10.dp)),
            TextColor1,
            0.3f
        )
        Column(
            Modifier
                .padding(5.dp)
                .padding(10.dp)
        ) {
            ShimmerEffect(
                Modifier
                    .fillMaxWidth(0.8f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp)),
                TextColor1,
                0.3f
            )
            Spacer(5)
            ShimmerEffect(
                Modifier
                    .fillMaxWidth(0.5f)
                    .height(15.dp)
                    .clip(RoundedCornerShape(10.dp)),
                TextColor1,
                0.3f
            )
            Spacer(20)
            ShimmerEffect(
                Modifier
                    .fillMaxWidth(0.3f)
                    .height(10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                TextColor1,
                0.3f
            )
            Spacer(20)
            ShimmerEffect(
                Modifier
                    .fillMaxWidth(0.6f)
                    .height(15.dp)
                    .clip(RoundedCornerShape(10.dp)),
                TextColor1,
                0.3f
            )
        }
    }
}