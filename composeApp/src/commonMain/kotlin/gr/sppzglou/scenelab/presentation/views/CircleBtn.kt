package gr.sppzglou.scenelab.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.scenelab.Clr.AccentColor
import gr.sppzglou.scenelab.Clr.SecondaryColor

@Composable
fun CircleBtn(ico: Any, size: Int = 40, padding: Int = 10, action: suspend () -> Unit) {
    ImageView(
        ico,
        Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(SecondaryColor)
            .Click {
                action()
            }
            .padding(padding.dp),
        colorFilter = ColorFilter.tint(AccentColor)
    )
}