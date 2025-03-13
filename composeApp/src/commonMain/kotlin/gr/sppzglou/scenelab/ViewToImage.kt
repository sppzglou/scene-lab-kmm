package gr.sppzglou.scenelab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.ImageBitmap

expect class ViewToImage() {

    fun capture(onComplete: (image: ImageBitmap) -> Unit)

    @Composable
    fun CapturingView(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    )
}

fun Modifier.drawWithCacheIf(flag: Boolean, onBuildDrawCache: CacheDrawScope.() -> DrawResult) =
    if (flag) this.drawWithCache(onBuildDrawCache) else this

//expect fun String.toImage(): ImageBitmap?