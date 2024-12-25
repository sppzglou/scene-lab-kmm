package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int,
    val results: List<MovieDto>
)