package gr.sppzglou.scenelab.presentation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture

/*actual class ViewToImage {
    private val capture = mutableStateOf(false)
    private var onComplete: (ImageBitmap) -> Unit = {}

    actual fun capture(onComplete: (image: ImageBitmap) -> Unit) {
        log("ccvce11", this@ViewToImage.hashCode())
        this.onComplete = onComplete
        capture.value = true
    }

    @Composable
    actual fun CapturingView(
        modifier: Modifier,
        content: @Composable () -> Unit
    ) {
        Launch {
            log("ccvce22", this@ViewToImage.hashCode())
        }
        val picture = remember{ Picture() }
        Box(modifier.drawWithCacheIf(capture.value) {
            val width = this.size.width.toInt()
            val height = this.size.height.toInt()
            onDrawWithContent {
                val pictureCanvas =
                    androidx.compose.ui.graphics.Canvas(
                        picture.beginRecording(
                            width,
                            height
                        )
                    )
                draw(this, this.layoutDirection, pictureCanvas, this.size) {
                    this@onDrawWithContent.drawContent()
                }
                picture.endRecording()

                drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }

                val bitmap = createBitmapFromPicture(picture)
                onComplete(bitmap.asImageBitmap())

                capture.value = false
            }
        }) {
            content()
        }
    }
}*/

fun createBitmapFromPicture(picture: Picture): Bitmap {
    val bitmap = Bitmap.createBitmap(
        picture.width,
        picture.height,
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    canvas.drawColor(android.graphics.Color.WHITE)
    canvas.drawPicture(picture)
    return bitmap
}