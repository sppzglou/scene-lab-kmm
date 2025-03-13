package gr.sppzglou.scenelab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import gr.sppzglou.composebooster.mutableRem

@Composable
actual fun PredictiveBack(back: suspend (side: PredictiveBackSide, progress: Float) -> Unit) {
    val kmpBack = LocalBackPress.current
    var side by mutableRem {
        PredictiveBackSide.Unknown
    }
    var progress by mutableRem(0f)

    LaunchedEffect(kmpBack.size.value, kmpBack.side.value) {
        side = kmpBack.side.value
        progress = kmpBack.size.value * 0.02f
    }

    LaunchedEffect(side, progress) {
        back(side, progress)
    }
}