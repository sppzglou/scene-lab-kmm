package gr.sppzglou.scenelab.presentation.screens.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.sppzglou.composebooster.Spacer
import gr.sppzglou.composebooster.tStr
import gr.sppzglou.scenelab.Clr.BackgroundColor
import gr.sppzglou.scenelab.Clr.TextColor1
import gr.sppzglou.scenelab.Clr.TextColor2
import gr.sppzglou.scenelab.presentation.views.ImageView

@Composable
fun PersonItemView(name: String, photo: String?, description: String? = null) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        ImageView(
            photo,
            Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(BackgroundColor)
        )
        Spacer()
        Column {
            Text(
                name.tStr(),
                Modifier.fillMaxWidth(),
                color = TextColor1,
                fontWeight = FontWeight.Bold
            )
            description?.let {
                Text(description, Modifier.fillMaxWidth(), color = TextColor2, fontSize = 12.sp)
            }
        }
    }
}

