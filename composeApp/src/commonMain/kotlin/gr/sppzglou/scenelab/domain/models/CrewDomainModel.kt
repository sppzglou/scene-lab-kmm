package gr.sppzglou.scenelab.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CrewDomainModel(
    val name: String,
    val photo: String?,
    val job: String
)