package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.composebooster.LocalWebDialog
import gr.sppzglou.composebooster.OnBackPress
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.SpacerNav
import gr.sppzglou.composebooster.bottomsheet.rememberBottomSheetState
import gr.sppzglou.scenelab.Clr.PrimaryColor
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.Clr.TextColor2
import gr.sppzglou.scenelab.presentation.BottomSheet
import gr.sppzglou.scenelab.presentation.models.ReviewUiModel

@Composable
fun ReviewsView(reviews: List<ReviewUiModel>) {
    val sheet = rememberBottomSheetState()
    val webView = LocalWebDialog.current

    Column(Modifier.padding(horizontal = 20.dp)) {
        Spacer(20)
        CategoryItemView("Reviews") {
            sheet.show()
        }
    }

    BottomSheet(sheet) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            items(reviews) {
                Column(Modifier.padding(vertical = 10.dp)) {
                    Text(
                        it.author,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor
                    )
                    Text(
                        it.date,
                        color = TextColor2,
                        fontSize = 12.sp
                    )
                    Text(
                        "source",
                        Modifier.Click {
                            webView.show(it.url)
                        },
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor
                    )

                    Spacer()
                    Text(
                        it.content, color = TextColor1
                    )
                }
            }
            SpacerNav(20)
        }

        OnBackPress {
            sheet.hide()
        }
    }
}