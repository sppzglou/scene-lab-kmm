package gr.sppzglou.scenelab.presentation.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import gr.sppzglou.composebooster.ImageViewLib
import scenelab.composeapp.generated.resources.Res
import scenelab.composeapp.generated.resources.error

@Composable
fun ImageView(
    data: Any?,
    modifier: Modifier = Modifier,
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    errorColor: Color = Color.Red,
    loadingColor: Color = Color.Gray
) {
    ImageViewLib(
        data = data,
        modifier = modifier,
        colorFilter = colorFilter,
        contentScale = contentScale,
        errorColor = errorColor,
        loadingColor = loadingColor,
        errorIcon = Res.drawable.error
    )
}