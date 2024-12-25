package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.scenelab.Clr.AccentColor
import gr.sppzglou.scenelab.Clr.SecondaryColor
import gr.sppzglou.scenelab.presentation.views.ImageView
import scenelab.composeapp.generated.resources.Res
import scenelab.composeapp.generated.resources.back

@Composable
fun CategoryItemView(txt: String, click: suspend () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(SecondaryColor)
            .Click { click() }
            .padding(vertical = 10.dp, horizontal = 20.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically) {
        Text(txt, color = AccentColor, fontWeight = FontWeight.Bold)
        ImageView(
            Res.drawable.back,
            Modifier.rotate(270f).size(30.dp),
            ColorFilter.tint(AccentColor),
        )
    }
}