package gr.sppzglou.scenelab.presentation.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import gr.sppzglou.composebooster.Click
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.collect
import gr.sppzglou.composebooster.rememberDataStore
import gr.sppzglou.composebooster.set
import gr.sppzglou.composebooster.then
import gr.sppzglou.scenelab.Clr.PrimaryColor
import gr.sppzglou.scenelab.Clr.THEME_MODE
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.ThemeMode
import gr.sppzglou.scenelab.presentation.views.ImageView
import scenelab.composeapp.generated.resources.Res
import scenelab.composeapp.generated.resources.dark
import scenelab.composeapp.generated.resources.light
import scenelab.composeapp.generated.resources.system

@Composable
fun ThemeDialogView() {
    val dataStore = rememberDataStore()
    val theme by dataStore.collect(THEME_MODE, ThemeMode.System.ordinal) {
        ThemeMode.entries[it]
    }

    Row(
        Modifier.fillMaxWidth().padding(20.dp).navigationBarsPadding(),
        Arrangement.SpaceAround,
        Alignment.CenterVertically
    ) {
        val list = remember {
            listOf(
                Pair(Res.drawable.light, ThemeMode.Light),
                Pair(Res.drawable.system, ThemeMode.System),
                Pair(Res.drawable.dark, ThemeMode.Dark)
            )
        }
        list.forEach {
            val isSelected = remember(theme) { theme == it.second }
            Column(
                Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageView(
                    it.first,
                    Modifier
                        .aspectRatio(0.55f)
                        .border(
                            1.dp,
                            isSelected then PrimaryColor or TextColor1,
                            RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .Click {
                            dataStore.set(THEME_MODE, it.second.ordinal)
                        },
                )
                Spacer()
                Text(
                    it.second.name,
                    Modifier
                        .clip(CircleShape)
                        .background(isSelected then PrimaryColor or Transparent)
                        .padding(5.dp),
                    color = TextColor1
                )
            }
            if (it != list.last()) {
                Spacer(20)
            }
        }
    }
}