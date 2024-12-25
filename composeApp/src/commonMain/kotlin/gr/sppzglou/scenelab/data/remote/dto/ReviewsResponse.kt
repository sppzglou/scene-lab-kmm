package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReviewsResponse(
    val results: List<ReviewDto>
)