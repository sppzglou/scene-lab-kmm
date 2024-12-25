package gr.sppzglou.scenelab

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import gr.sppzglou.scenelab.presentation.models.ListViewUiModel
import gr.sppzglou.scenelab.presentation.screens.list.ListView

object ListViewUiModelPreviewData {
    val previewListViewUiModel = ListViewUiModel(
        id = 0,
        title = "Preview Title",
        originalTitle = "Preview Original Title",
        releaseDate = "2024-12-18",
        voteAverage = 8.5,
        poster = "",
        isFavorite = false,
        page = 1
    )
}

@Preview
@Composable
fun Preview() {
    Column {
        (1..2).forEach { _ ->
            ListView(ListViewUiModelPreviewData.previewListViewUiModel, {

            }) {

            }
        }
    }
}