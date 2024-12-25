package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    val author: String,
//    @SerialName("author_details")
//    val authorDetails: AuthorDetails,
    val content: String,
    val id: String,
    @SerialName("created_at")
    val date: String,
    val url: String
)