package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1")
    val isoCode: String,
    @SerialName("name")
    val name: String
)