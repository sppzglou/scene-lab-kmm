package gr.sppzglou.scenelab.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CreditsDomainModel(
    val cast: List<CastDomainModel>,
    val crew: List<CrewDomainModel>
)