package gr.sppzglou.scenelab.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.bottomsheet.rememberBottomSheetState
import gr.sppzglou.scenelab.Clr.PrimaryColor
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.presentation.BottomSheet
import gr.sppzglou.scenelab.presentation.navigate
import gr.sppzglou.scenelab.presentation.navigator
import gr.sppzglou.scenelab.presentation.views.ImageView
import scenelab.composeapp.generated.resources.Res
import scenelab.composeapp.generated.resources.favorites
import scenelab.composeapp.generated.resources.logo
import scenelab.composeapp.generated.resources.theme

@Composable
fun BarView(modifier: Modifier) {
    val nav = navigator()
    val themeSheet = rememberBottomSheetState()

    Row(
        modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(50.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Row(Modifier.padding(start = 5.dp), verticalAlignment = Alignment.CenterVertically) {
            ImageView(
                Res.drawable.logo,
                Modifier.padding(5.dp).fillMaxHeight().aspectRatio(1f)
            )
            Spacer()
            Text(
                "SceneLab",
                color = TextColor1,
                fontSize = 26.sp,
                fontWeight = FontWeight.Thin
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageView(
                Res.drawable.favorites,
                Modifier.Click(bounded = false) {
                    nav.navigate(FavoritesListScreen())
                }.padding(5.dp).fillMaxHeight(0.8f).aspectRatio(1f),
                ColorFilter.tint(PrimaryColor)
            )
            Spacer()
            ImageView(
                Res.drawable.theme,
                Modifier.Click(bounded = false) {
                    themeSheet.show()
                }.padding(7.dp).fillMaxHeight(0.8f).aspectRatio(1f),
                ColorFilter.tint(PrimaryColor)
            )
        }
    }

    BottomSheet(themeSheet) {
        ThemeDialogView()
    }
}