package gr.sppzglou.scenelab

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import gr.sppzglou.composebooster.AndroidBackPressHandler
import gr.sppzglou.composebooster.ImageViewLib
import gr.sppzglou.composebooster.mutableRem
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

val LocalBackPress = staticCompositionLocalOf { AppBack() }

data class AppBack(
    val size: MutableState<Float> = mutableStateOf(0f),
    val side: MutableState<PredictiveBackSide> = mutableStateOf(PredictiveBackSide.Unknown),
    var actions: MutableList<(suspend () -> Unit)> = mutableStateListOf()
) {
    fun addAction(action: (suspend () -> Unit)) {
        this.actions.add(action)
    }

    fun removeAction(action: (suspend () -> Unit)) {
        this.actions.removeAt(this.actions.indexOf(action))
    }
}

@Composable
fun OnBackPress(action: suspend () -> Unit) {
    val back = LocalBackPress.current

    DisposableEffect(Unit) {

        back.addAction(action)

        onDispose {
            back.removeAction(action)
        }
    }

    AndroidBackPressHandler(action)
}

@Composable
fun InitBackPress(
    icoBg: DrawableResource,
    ico: DrawableResource,
    bg: Color = Gray,
    tint: Color = Black,
    content: @Composable () -> Unit
) {
    val back by mutableRem(AppBack())

    CompositionLocalProvider(
        LocalBackPress provides back
    ) {
        Box(Modifier.fillMaxSize()) {
            content()
            SwipeBack(icoBg, ico, bg, tint)
        }
    }
}

@Composable
fun SwipeBack(
    icoBg: DrawableResource,
    ico: DrawableResource,
    bg: Color = Gray,
    tint: Color = Black
) {
    val haptic = LocalHapticFeedback.current
    val back = LocalBackPress.current
    val nav = LocalNavigator.currentOrThrow

    if (back.actions.isNotEmpty() || nav.canPop) {
        val scope = rememberCoroutineScope()
        var sizeDp by mutableRem(0.dp)
        var top by mutableRem(0.dp)
        val size by animateDpAsState(sizeDp)
        val height = 200

        LaunchedEffect(sizeDp) {
            back.size.value = sizeDp.value
        }

        var vibrate by mutableRem(false)

        fun back() = scope.launch {
            back.actions.lastOrNull()?.invoke() ?: nav.pop()
            back.size.value = 0f
        }

        AndroidBackPressHandler {
            back()
        }

        Box(
            Modifier
                .zIndex(100f)
                .width(10.dp)
                .fillMaxHeight()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            if (sizeDp > 30.dp) {
                                back()
                            }
                            sizeDp = 0.dp
                        },
                        onDragCancel = {
                            sizeDp = 0.dp
                        }
                    ) { change, _ ->
                        back.side.value = PredictiveBackSide.Left
                        val tempTop = change.position.y.toDp() - (height * 0.5).dp
                        top = if (tempTop < 0.dp) 0.dp else tempTop

                        val dp = change.position.x.toDp()
                        if (dp <= 50.dp) sizeDp = dp
                        vibrate = dp > 30.dp
                    }
                }
        )
        Row(Modifier.fillMaxWidth(), Arrangement.End) {
            Box(
                Modifier
                    .zIndex(100f)
                    .width(10.dp)
                    .fillMaxHeight()
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onDragEnd = {
                                if (sizeDp > 30.dp) {
                                    back()
                                }
                                sizeDp = 0.dp
                            },
                            onDragCancel = {
                                sizeDp = 0.dp
                            }
                        ) { change, _ ->
                            back.side.value = PredictiveBackSide.Right
                            val tempTop = change.position.y.toDp() - (height * 0.5).dp
                            top = if (tempTop < 0.dp) 0.dp else tempTop

                            val dp = (change.position.x * -1).toDp()
                            if (dp <= 50.dp) sizeDp = dp
                            vibrate = dp > 30.dp
                        }
                    }
            )
        }

        LaunchedEffect(vibrate) {
            if (vibrate) haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }

        LaunchedEffect(back.side.value) {
            println(back.side.value)
        }

        if (back.side.value == PredictiveBackSide.Left) {
            Column(
                Modifier.zIndex(100f).padding(top = top).height(height.dp),
                Arrangement.Center
            ) {
                Box(Modifier.size(size, height.dp)) {
                    ImageViewLib(
                        data = icoBg,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        colorFilter = ColorFilter.tint(bg)
                    )
                    Column(Modifier.fillMaxSize(), Arrangement.Center) {
                        ImageViewLib(
                            data = ico,
                            modifier = Modifier.size((size.value * 0.6f).dp),
                            colorFilter = ColorFilter.tint(tint)
                        )
                    }
                }
            }
        }
        if (back.side.value == PredictiveBackSide.Right) {
            Row(Modifier.fillMaxWidth().padding(top = top), Arrangement.End) {
                Column(
                    Modifier.zIndex(100f).rotate(180f).height(height.dp),
                    Arrangement.Center
                ) {
                    Box(Modifier.size(size, height.dp)) {
                        ImageViewLib(
                            data = icoBg,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds,
                            colorFilter = ColorFilter.tint(bg)
                        )
                        Column(Modifier.fillMaxSize(), Arrangement.Center) {
                            ImageViewLib(
                                data = ico,
                                modifier = Modifier.size((size.value * 0.6f).dp),
                                colorFilter = ColorFilter.tint(tint)
                            )
                        }
                    }
                }
            }
        }
    }
}