package gr.sppzglou.scenelab.presentation.views

import androidx.compose.runtime.Composable
import gr.sppzglou.scenelab.presentation.navigator
import gr.sppzglou.scenelab.presentation.popBackStack
import scenelab.composeapp.generated.resources.Res
import scenelab.composeapp.generated.resources.back

@Composable
fun BackBtn() {
    val nav = navigator()
    CircleBtn(Res.drawable.back) {
        nav.popBackStack()
    }
}