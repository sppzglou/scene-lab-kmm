package gr.sppzglou.scenelab.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

interface BaseScreen : Screen {

    @Composable
    fun BoxScope.View()

    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize()) {
            View()
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
