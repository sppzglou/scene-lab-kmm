package gr.sppzglou.scenelab.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorites")
data class FavoriteEntity(
    @PrimaryKey
    val id: Int
)