package gr.sppzglou.scenelab

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import gr.sppzglou.composebooster.mutableRem
import gr.sppzglou.scenelab.Clr.BackgroundColor


//@Composable
//fun FadeTransition(
//    content: ScreenTransitionContent = { it.Content() }
//) {
//    ScreenTransition(
//        navigator = navigator,
//        modifier = modifier,
//        content = content,
//        transition = { fadeIn(animationSpec = animationSpec) togetherWith fadeOut(animationSpec = animationSpec) }
//    )
//}

abstract class BaseScreen : Screen {
    var emptyNav = false

    @Composable
    open fun BoxScope.View() {
    }

    @Composable
    fun Ok() {
        Box {
            View()
        }
    }

    @Composable
    override fun Content() {
        val nav = navigatorOrNull(emptyNav)
        var size by mutableRem(0.dp)
        var side by mutableRem(PredictiveBackSide.Left)
        val x by animateDpAsState(
            if (side == PredictiveBackSide.Right) -size
            else size
        )
        val s by animateFloatAsState(1f - (size.value / 50f) * 0.2f)

        PredictiveBack { sid, progress ->
            //  println(sid)
            side = sid
            size = (progress / 0.02f).dp
        }
        Box {
//            // **Αποθηκεύουμε το προηγούμενο screen ως composable χωρίς να το ξανατρέχουμε**
            val prevScreen = remember(nav?.items) {
                nav?.items?.getOrNull(nav.items.size - 2)
            }
            val content: ScreenTransitionContent = remember { { it.Content() } }
//            val transition = updateTransition(targetState = x > 0.dp, label = "Visibility Transition")
//
//            val alpha by transition.AnimatedContent (
//
//            ) { state ->
//
//            }

//            AnimatedContent(
//                targetState = navigator.lastItem,
//                transitionSpec = transition,
//                modifier = modifier
//            ) { screen ->
//                navigator.saveableState("transition", screen) {
//                    content(screen)
//                }
//            }

            prevScreen?.let {
                (prevScreen as? BaseScreen)?.emptyNav = true
                nav?.saveableState(prevScreen.key, prevScreen) {
                    prevScreen.Content()
//                (prevScreen as BaseScreen).Ok()
//                Box(Modifier.fillMaxSize().background(Color.Black.copy(0.5f)))
                    //}
                }
            }

            Box(Modifier.offset(x = x).scale(s).clip(RoundedCornerShape(15.dp))) {
                Box(Modifier.fillMaxSize().background(BackgroundColor)) {
                    View()
                }
            }

        }
    }
}

fun Navigator.popBackStack() = this.pop()

fun <T> Navigator.popBackStack(screen: T, inclusive: Boolean = false): Boolean {
    return this.popUntil { it::class == screen }.run {
        if (this && inclusive) pop() else this
    }
}

fun Navigator.navigate(screen: Screen) {
    if (lastItem.key != screen.key) this.push(screen)
}

@Composable
fun navigator() = LocalNavigator.currentOrThrow

@Composable
fun navigatorOrNull(empty: Boolean) = if (empty) null else LocalNavigator.currentOrThrow
