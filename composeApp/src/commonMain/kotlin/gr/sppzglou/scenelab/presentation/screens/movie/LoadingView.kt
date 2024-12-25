package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.ScreenOrientation
import gr.sppzglou.composebooster.ShimmerEffect
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.checkOrientation
import gr.sppzglou.composebooster.dpToPx
import gr.sppzglou.composebooster.getScreenOrientation
import gr.sppzglou.composebooster.mutableRem
import gr.sppzglou.composebooster.pxToDp
import gr.sppzglou.composebooster.safePadding
import gr.sppzglou.composebooster.widthPx
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.presentation.models.MovieUiModel

@Composable
fun LoadingView(coverHeight: Float, movie: MovieUiModel?, scrollState: LazyListState) {
    AnimatedVisibility(
        movie == null,
        exit = fadeOut(animationSpec = tween(durationMillis = 3000)),
    ) {
        if (scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset == 0) {
            LazyColumn(Modifier.fillMaxSize()) {
                item {
                    Box {
                        ShimmerEffect(
                            Modifier
                                .padding(top = (coverHeight.pxToDp * 0.5).dp, start = 20.dp)
                                .fillMaxWidth(checkOrientation(0.4f, 0.2f))
                                .aspectRatio(0.66f)
                                .clip(RoundedCornerShape(10.dp)),
                            TextColor1,
                            0.3f
                        )
                        if (getScreenOrientation() != ScreenOrientation.Portrait) {
                            Row(
                                Modifier.fillMaxWidth()
                                    .padding(
                                        top = safePadding(coverHeight.pxToDp - 25).dp,
                                        start = 30.dp
                                    ),
                                Arrangement.End
                            ) {
                                Box(Modifier.fillMaxWidth(0.8f)) {
                                    ShimmerEffect(
                                        Modifier.fillMaxWidth(0.8f).height(50.dp).clip(CircleShape),
                                        TextColor1,
                                        0.3f
                                    )
                                }
                            }
                        }
                    }
                    if (getScreenOrientation() == ScreenOrientation.Portrait) {
                        Spacer(20)
                        Column(Modifier.padding(horizontal = 20.dp)) {
                            var w by mutableRem(0)
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .onSizeChanged {
                                        w = it.width
                                    }
                            ) {
                                Column(Modifier.widthPx { w - 70.dpToPx }) {
                                    ShimmerEffect(
                                        Modifier.fillMaxWidth(0.8f).height(20.dp).clip(CircleShape),
                                        TextColor1,
                                        0.3f
                                    )
                                    Spacer(10)
                                    ShimmerEffect(
                                        Modifier.fillMaxWidth(0.8f).height(20.dp).clip(CircleShape),
                                        TextColor1,
                                        0.3f
                                    )
                                }
                            }
                            Spacer(20)
                            ShimmerEffect(
                                Modifier.fillMaxWidth(0.8f).height(20.dp).clip(CircleShape),
                                TextColor1,
                                0.3f
                            )
                        }
                    }
                }
            }
        }
    }
}