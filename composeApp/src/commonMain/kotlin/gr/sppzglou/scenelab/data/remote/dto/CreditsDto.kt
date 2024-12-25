package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreditsDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)