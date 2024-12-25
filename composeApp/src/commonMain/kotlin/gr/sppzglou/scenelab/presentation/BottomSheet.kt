package gr.sppzglou.scenelab.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.applyIf
import gr.sppzglou.composebooster.bottomsheet.BottomSheetDialog
import gr.sppzglou.composebooster.bottomsheet.BottomSheetState
import gr.sppzglou.scenelab.Clr.SheetColor

@Composable
fun BottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier,
    scrimColor: Color = Color.Black.copy(alpha = 0.32f),
    defaultStyle: Boolean = true,
    sheetContent: @Composable () -> Unit
) {
    val bg = SheetColor
    BottomSheetDialog(
        modifier,
        state,
        scrimColor
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .applyIf(defaultStyle) {
                    it
                        .clip(RoundedCornerShape(15.dp, 15.dp))
                        .background(bg)
                }) {
            sheetContent()
        }
    }
}