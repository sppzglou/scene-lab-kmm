package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    @SerialName("original_name")
    val name: String,
    @SerialName("profile_path")
    val photo: String?,
    val character: String = "Unknown"
)