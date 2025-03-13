package gr.sppzglou.scenelab.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.ComposeUIViewController
import gr.sppzglou.composebooster.pxToDp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.Image
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSData
import platform.UIKit.UIGraphicsImageRenderer
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.UIKit.UIView
import platform.posix.memcpy

actual class ViewToImage {
    private val capture = mutableStateOf(false)
    private var onComplete: (ImageBitmap) -> Unit = {}

    actual fun capture(onComplete: (image: ImageBitmap) -> Unit) {
        this.onComplete = onComplete
        capture.value = true
    }

    @OptIn(ExperimentalComposeApi::class)
    @Composable
    actual fun CapturingView(
        modifier: Modifier,
        content: @Composable () -> Unit
    ) {
        var image by remember { mutableStateOf<ImageBitmap?>(null) }
        var width by remember { mutableStateOf(0f) }
        var height by remember { mutableStateOf(0f) }

        val view = ComposeUIViewController({
            opaque = false
        }) {
            content()
        }.view

        Box(
            Modifier.onSizeChanged {
                width = it.width.pxToDp
                height = it.height.pxToDp
            }.then(modifier)
        ) {
            content()
        }

        if (capture.value) {
            image = view.toUiImage(IntSize(width.toInt(), height.toInt())).toImageBitmap()
        }

        LaunchedEffect(image, capture.value) {
            if (image != null && capture.value) {
                capture.value = false
                onComplete(image!!)
            }
        }
    }


    @OptIn(ExperimentalForeignApi::class)
    fun UIImage.toImageBitmap(): ImageBitmap? {
        val imageData: NSData = UIImagePNGRepresentation(this) ?: return null

        // Αν η εικόνα δεν μπορεί να μετατραπεί σε NSData, επιστρέφουμε null

        val length = imageData.length.toInt()
        val byteArray = ByteArray(length)

        // Χρήση memcpy για αντιγραφή των δεδομένων στο ByteArray
        byteArray.usePinned { pinned ->
            memcpy(pinned.addressOf(0), imageData.bytes, length.convert())
        }

        // Δημιουργία Skia Image από το ByteArray
        val skiaImage = Image.makeFromEncoded(byteArray)

        return skiaImage.toComposeImageBitmap()
    }
}

@OptIn(ExperimentalForeignApi::class)
fun UIView.toUiImage(size: IntSize): UIImage {
    val width = size.width.toDouble()
    val height = size.height.toDouble()
    val cgSize = CGSizeMake(width, height)

    setBounds(CGRectMake(0.0, 0.0, width, height))

    val renderer = UIGraphicsImageRenderer(size = cgSize)

    return renderer.imageWithActions { context ->
        drawViewHierarchyInRect(bounds, afterScreenUpdates = true)
    }
}