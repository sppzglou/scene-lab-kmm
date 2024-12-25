package gr.sppzglou.scenelab.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CastDomainModel(
    val name: String,
    val photo: String?,
    val character: String
)