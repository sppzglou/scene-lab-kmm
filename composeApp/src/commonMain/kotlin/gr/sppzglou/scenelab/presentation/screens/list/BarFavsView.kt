package gr.sppzglou.scenelab.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.presentation.views.BackBtn

@Composable
fun BarPopularView(modifier: Modifier) {

    Row(
        modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(50.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Row(Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            BackBtn()
            Spacer()
            Text(
                "Favorites",
                color = TextColor1,
                fontSize = 26.sp,
                fontWeight = FontWeight.Thin
            )
        }
    }
}