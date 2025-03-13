package gr.sppzglou.scenelab

import androidx.compose.runtime.Composable

enum class PredictiveBackSide {
    Unknown,
    Left,
    Right
}

@Composable
expect fun PredictiveBack(back: suspend (side: PredictiveBackSide, progress: Float) -> Unit)