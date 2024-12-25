package gr.sppzglou.scenelab.presentation.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.composebooster.Shape
import gr.sppzglou.composebooster.mutableRem
import gr.sppzglou.scenelab.Clr.PrimaryColor

@Composable
fun FavBtn(isFav: Boolean, size: Int = 40, function: () -> Unit) {
    val favShape = remember { Fav }
    var scaleAnim by mutableRem(false)

    val bg by animateColorAsState(
        if (!isFav) PrimaryColor.copy(0f) else PrimaryColor,
        tween(500),
        label = ""
    )
    val scale by animateFloatAsState(if (scaleAnim) 1.5f else 1f, tween(250), label = "") {
        if (scaleAnim) scaleAnim = false
    }

    Box(
        Modifier
            .scale(scale)
            .size(size.dp)
            .border(1.dp, PrimaryColor, favShape.v)
            .clip(favShape.v)
            .background(bg)
            .Click {
                scaleAnim = true
                function()
            }
    )

}

val Fav = Shape(
    "M26.74,5.73c6.8,0.01,12.63,2.46,17.64,6.99c2.13,1.93,3.94,4.12,5.46,6.56c0.32,0.51,0.48,0.41,0.76-0.03 c2.91-4.56,6.63-8.26,11.48-10.74c3.61-1.85,7.43-2.78,11.5-2.78c5.22-0.01,10.04,1.33,14.35,4.27c6.11,4.17,9.74,10,10.54,17.32 c0.9,8.25-0.17,16.28-4.04,23.76c-1.75,3.37-4.06,6.35-6.6,9.15c-4.34,4.77-9.24,8.95-14.09,13.18 c-5.4,4.71-10.85,9.37-16.13,14.21c-2.29,2.1-4.58,4.21-6.85,6.34c-0.38,0.36-0.63,0.42-1.04,0.03 c-3.67-3.46-7.37-6.89-11.16-10.22c-4.27-3.75-8.56-7.47-12.84-11.21c-4.54-3.97-9.1-7.93-13.18-12.4 c-4.62-5.07-8.13-10.73-9.66-17.5c-0.97-4.3-1.38-8.67-1.17-13.06c0.32-6.65,2.78-12.43,7.63-17.06 c4.02-3.84,8.86-5.99,14.38-6.65C24.71,5.76,25.72,5.76,26.74,5.73z",
    100F, 100F
)