package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class CreditsResponse(
    val credits: CreditsDto
)