package gr.sppzglou.scenelab

import androidx.activity.BackEventCompat
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import gr.sppzglou.composebooster.mutableRem

@Composable
actual fun PredictiveBack(back: suspend (side: PredictiveBackSide, progress: Float) -> Unit) {
    val kmpBack = LocalBackPress.current
    var side by mutableRem(PredictiveBackSide.Unknown)

    var progress by mutableRem(0f)

    PredictiveBackHandler { event ->
        event.collect {
            side = when (it.swipeEdge) {
                BackEventCompat.EDGE_LEFT -> PredictiveBackSide.Left
                BackEventCompat.EDGE_RIGHT -> PredictiveBackSide.Right
                else -> PredictiveBackSide.Unknown
            }
            progress = it.progress
        }
    }

    LaunchedEffect(kmpBack.size.value, kmpBack.side.value) {
        side = kmpBack.side.value
        progress = kmpBack.size.value * 0.02f
    }

    LaunchedEffect(side, progress) {
        back(side, progress)
    }
}