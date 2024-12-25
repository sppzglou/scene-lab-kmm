package gr.sppzglou.scenelab.domain.models

data class ReviewDomainModel(
    val author: String,
    val content: String,
    val id: String,
    val date: String,
    val url: String
)