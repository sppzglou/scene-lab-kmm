package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.OnBackPress
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.SpacerNav
import gr.sppzglou.composebooster.bottomsheet.rememberBottomSheetState
import gr.sppzglou.scenelab.presentation.BottomSheet
import gr.sppzglou.scenelab.presentation.models.CastUiModel

@Composable
fun CastView(cast: List<CastUiModel>) {
    val sheet = rememberBottomSheetState()

    Column(Modifier.padding(horizontal = 20.dp)) {
        Spacer(20)
        CategoryItemView("Cast") {
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
            items(cast) {
                PersonItemView(it.name, it.photo, it.character)
            }
            SpacerNav(20)
        }

        OnBackPress {
            sheet.hide()
        }
    }
}