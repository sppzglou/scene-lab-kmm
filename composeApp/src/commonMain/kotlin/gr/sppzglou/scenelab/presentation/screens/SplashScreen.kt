package gr.sppzglou.scenelab.presentation.screens

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import gr.sppzglou.composebooster.Launch
import gr.sppzglou.scenelab.presentation.BaseScreen
import gr.sppzglou.scenelab.presentation.navigator
import gr.sppzglou.scenelab.presentation.screens.list.MoviesListScreen
import gr.sppzglou.scenelab.presentation.views.ImageView
import kotlinx.coroutines.delay
import scenelab.composeapp.generated.resources.Res
import scenelab.composeapp.generated.resources.logo

class SplashScreen : BaseScreen {

    @Composable
    override fun BoxScope.View() {
        val nav = navigator()

        Launch {
            delay(1000)
            nav.replace(MoviesListScreen())
        }

        ImageView(
            Res.drawable.logo,
            Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.4f)
                .aspectRatio(1f)
        )
    }
}